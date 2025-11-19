package com.group3.posts.presentation.service;

import com.group3.config.PrefixedUUID;
import com.group3.entity.*;
import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import com.group3.posts.config.helpers.SecretKeyHelper;
import com.group3.posts.data.repository.*;
import com.group3.posts.domain.dto.comment.mapper.CommentMapper;
import com.group3.posts.domain.dto.comment.request.CreateCommentReq;
import com.group3.posts.domain.dto.comment.request.DeleteCommentReq;
import com.group3.posts.domain.dto.comment.request.GetCommentByIdReq;
import com.group3.posts.domain.dto.comment.request.GetCommentPageReq;
import com.group3.posts.domain.dto.comment.request.ToggleCommentVotesReq;
import com.group3.posts.domain.dto.comment.response.CreateCommentRes;
import com.group3.posts.domain.dto.comment.response.GetCommentByIdRes;
import com.group3.posts.domain.dto.comment.response.GetCommentPageRes;
import com.group3.posts.domain.dto.comment.response.ToggleCommentVotesRes;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class CommentService implements CommentServiceI {

    private final CommentRepository commentRepository;

    private final PostsRepository postsRepository;

    private final UserRepository userRepository;

    private final PageProfileRepository pageProfileRepository;

    private final NotificationsRepository notificationsRepository;

    private final SecretKeyHelper secretKeyHelper;


    // ======== Create Comment ========

    @Override
    public CreateCommentRes create(CreateCommentReq dto) {
        User author = this.userRepository.auth(dto.getToken());
        if (author == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        Post post = this.postsRepository.getById(dto.getPostId());
        if (post == null) throw new ErrorHandler(ErrorType.POST_NOT_FOUND);
        if (post.getStatus() != Status.ACTIVE) throw new ErrorHandler(ErrorType.POST_NOT_ACTIVE);

        Comment comment = new Comment();

        PrefixedUUID.EntityType type = PrefixedUUID.resolveType(UUID.fromString(dto.getProfileId()));
        if (type == PrefixedUUID.EntityType.USER) {
            if (!author.getId().equals(dto.getProfileId())) {
                throw new ErrorHandler(ErrorType.UNAUTHORIZED);
            };
            comment.setPageProfile(PageProfile.builder().id(null).build());
        }
        else if (type == PrefixedUUID.EntityType.PAGE) {
            PageProfile page = this.pageProfileRepository.getById(dto.getProfileId(), dto.getToken());
            if (page.getMembers().stream().noneMatch(member -> member.getId().equals(author.getId()))) {
                throw new ErrorHandler(ErrorType.UNAUTHORIZED);
            }
            comment.setPageProfile(page);
        }

        comment.setId(PrefixedUUID.generate(PrefixedUUID.EntityType.COMMENT).toString());
        comment.setAuthor(author);
        comment.setPostId(post.getId());
        comment.setContent(dto.getContent());
        comment.setUpvoters(List.of());
        comment.setDownvoters(List.of());
        comment.setCreatedAt(LocalDateTime.now());
        comment.setUpdatedAt(LocalDateTime.now());
        comment.setStatus(Status.ACTIVE);

        if (dto.getReplyTo() != null && !dto.getReplyTo().isEmpty()) {
            Comment replyTo = this.commentRepository.getById(dto.getReplyTo());
            if (replyTo == null) throw new ErrorHandler(ErrorType.COMMENT_NOT_FOUND);
            comment.setReplyTo(replyTo);
        }

        Comment saved = this.commentRepository.save(comment);
        comment.setVotersToNull();
        comment.setId(saved.getId());

        String targetId;
        if (post.getPageProfile() != null && post.getPageProfile().getId() != null) {
            targetId = post.getPageProfile().getId();
        }
        else {
            targetId = post.getAuthor().getId();
        }

        this.notificationsRepository.create(
                secretKeyHelper.getSecret(),
                targetId,
                post.getId(),
                NotificationContent.COMMENT.name()
        );

        return CommentMapper.create().toResponse(comment);
    }


    // ======== Get Comment by ID ========

    @Override
    public GetCommentByIdRes getById(GetCommentByIdReq dto) {
        User user = this.userRepository.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        Comment comment = this.commentRepository.getById(dto.getCommentId());
        if (comment == null) throw new ErrorHandler(ErrorType.COMMENT_NOT_FOUND);

        if (comment.getAuthor().getId() != null) {
            User fullProfile = this.userRepository.getById(comment.getAuthor().getId(), dto.getToken());
            comment.setAuthor(fullProfile);
        }
        if (comment.getPageProfile().getId() != null) {
            PageProfile fullPage = this.pageProfileRepository.getById(comment.getPageProfile().getId(), dto.getToken());
            comment.setPageProfile(fullPage);
        }
        comment.setVotersToNull();

        return CommentMapper.getById().toResponse(comment);
    }


    // ======== Get Comments by Post ========

    @Override
    public GetCommentPageRes getComments(GetCommentPageReq dto) {
        Post post = this.postsRepository.getById(dto.getPostId());
        if (post == null) throw new ErrorHandler(ErrorType.POST_NOT_FOUND);

        PageContent<Comment> comments =
                this.commentRepository.getByPostId(dto.getPostId(), dto.getPage(), dto.getSize());

        comments.getContent().forEach(
                comment -> {
                    if (comment.getAuthor().getId() != null) {
                        User fullProfile = this.userRepository.getById(comment.getAuthor().getId(), dto.getToken());
                        comment.setAuthor(fullProfile);
                    }
                    if (comment.getPageProfile().getId() != null) {
                        PageProfile fullPage = this.pageProfileRepository.getById(comment.getPageProfile().getId(), dto.getToken());
                        comment.setPageProfile(fullPage);
                    }
                    comment.setVotersToNull();
                    comment.setReplies(this.commentRepository.getRepliesComment(comment.getId()));
                }
        );

        return CommentMapper.getPage().toResponse(comments);
    }


    // ======== Toggle Comment Votes ========

    @Override
    public ToggleCommentVotesRes toggleVotes(ToggleCommentVotesReq dto) {
        User user = this.userRepository.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        Comment comment = this.commentRepository.getById(dto.getCommentId());
        if (comment == null) throw new ErrorHandler(ErrorType.COMMENT_NOT_FOUND);

        String userId = user.getId();
        List<String> upvoters = comment.getUpvoters();
        List<String> downvoters = comment.getDownvoters();

        boolean isNewUpvote = false;
        boolean isNewDownvote = false;

        if (Vote.UPVOTE == dto.getVoteType()) {
            if (upvoters.contains(userId)) upvoters.remove(userId);
            else {
                upvoters.add(userId);
                downvoters.remove(userId);
                isNewUpvote = true;
            }
        }
        if (Vote.DOWNVOTE == dto.getVoteType()) {
            if (downvoters.contains(userId)) downvoters.remove(userId);
            else {
                downvoters.add(userId);
                upvoters.remove(userId);
                isNewDownvote = true;
            }
        }

        comment.setUpvoters(upvoters);
        comment.setDownvoters(downvoters);
        this.commentRepository.update(comment);

        Post post = this.postsRepository.getById(comment.getPostId());
        if (post == null) throw new ErrorHandler(ErrorType.POST_NOT_FOUND); // Should not happen if comment exists

        String targetId;
        if (post.getPageProfile() != null && post.getPageProfile().getId() != null) {
            targetId = post.getPageProfile().getId();
        }
        else {
            targetId = post.getAuthor().getId();
        }

        if (isNewUpvote) {
            this.notificationsRepository.create(
                    this.secretKeyHelper.getSecret(),
                    targetId,
                    post.getId(),
                    NotificationContent.UPVOTE.name()
            );
        }
        else if (isNewDownvote) {
            this.notificationsRepository.create(
                    this.secretKeyHelper.getSecret(),
                    targetId,
                    post.getId(),
                    NotificationContent.DOWNVOTE.name()
            );
        }


        if (comment.getAuthor() != null && comment.getAuthor().getId() != null) {
            User fullProfile = this.userRepository.getById(comment.getAuthor().getId(), dto.getToken());
            comment.setAuthor(fullProfile);
        }
        if (comment.getPageProfile() != null && comment.getPageProfile().getId() != null) {
            PageProfile fullPage = this.pageProfileRepository.getById(comment.getPageProfile().getId(), dto.getToken());
            comment.setPageProfile(fullPage);
        }

        comment.calculateVotersQuantity();
        comment.setVotersToNull();

        return CommentMapper.toggleVotes().toResponse(comment);
    }


    // ======== Delete Comment ========

    @Override
    public void delete(DeleteCommentReq dto) {
        User user = this.userRepository.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        Comment comment = this.commentRepository.getById(dto.getCommentId());
        if (comment == null || comment.getStatus() == Status.DELETED) {
            throw new ErrorHandler(ErrorType.COMMENT_NOT_FOUND);
        }

        if (!user.canDelete(comment)) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        comment.setUpdatedAt(LocalDateTime.now());
        comment.setStatus(Status.DELETED);

        this.commentRepository.update(comment);
    }

}
