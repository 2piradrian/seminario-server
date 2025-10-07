package com.group3.posts.presentation.service;

import com.group3.config.PrefixedUUID;
import com.group3.entity.*;
import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import com.group3.posts.data.repository.*;
import com.group3.posts.domain.dto.comment.mapper.CommentMapper;
import com.group3.posts.domain.dto.comment.request.*;
import com.group3.posts.domain.dto.comment.response.CreateCommentRes;
import com.group3.posts.domain.dto.comment.response.GetCommentPageRes;
import com.group3.posts.domain.dto.comment.response.ToggleCommentVotesRes;
import com.group3.posts.domain.dto.post.mapper.PostMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class CommentService implements CommentServiceI {

    private final CommentRepository commentRepository;

    private final PostsRepository postsRepository;

    private final UserRepository userRepository;

    private final ProfilesRepository profilesRepository;

    private final PagesRepository pagesRepository;

    @Override
    public GetCommentPageRes getComments(GetCommentPageReq dto) {
        Post post = this.postsRepository.getById(dto.getPostId());
        if (post == null) throw new ErrorHandler(ErrorType.POST_NOT_FOUND);

        PageContent<Comment> comments =
                this.commentRepository.getByPostId(dto.getPostId(), dto.getPage(), dto.getSize());

        comments.getContent().forEach(
            comment -> {
                if (comment.getAuthor().getId() != null) {
                    UserProfile fullProfile = this.profilesRepository.getById(comment.getAuthor().getId());
                    comment.setAuthor(fullProfile);
                }
                if (comment.getPage().getId() != null) {
                    Page fullPage = this.pagesRepository.getById(comment.getPage().getId());
                    comment.setPage(fullPage);
                }
            }
        );

        return CommentMapper.getPage().toResponse(comments);
    }

    @Override
    public CreateCommentRes create(CreateCommentReq dto) {
        User user = this.userRepository.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        Post post = this.postsRepository.getById(dto.getPostId());
        if (post == null) throw new ErrorHandler(ErrorType.POST_NOT_FOUND);

        if (post.getStatus() != Status.ACTIVE) throw new ErrorHandler(ErrorType.POST_NOT_ACTIVE);

        Comment comment = new Comment();
        UserProfile author = this.profilesRepository.getById(user.getId());

        PrefixedUUID.EntityType type = PrefixedUUID.resolveType(UUID.fromString(dto.getProfileId()));
        if (type == PrefixedUUID.EntityType.USER) {
            if (!user.getId().equals(dto.getProfileId())) throw new ErrorHandler(ErrorType.UNAUTHORIZED);
            comment.setPage(Page.builder().id(null).build());
            comment.setAuthor(author);
        }
        else if (type == PrefixedUUID.EntityType.PAGE) {
            Page page = this.pagesRepository.getById(dto.getProfileId());

            if (page.getMembers().stream().noneMatch(member -> member.getId().equals(user.getId()))) {
                throw new ErrorHandler(ErrorType.UNAUTHORIZED);
            }
            comment.setAuthor(author);
            comment.setPage(page);
        }

        comment.setPostId(post.getId());
        comment.setContent(dto.getContent());
        comment.setUpvoters(Set.of());
        comment.setDownvoters(Set.of());
        comment.setCreatedAt(LocalDateTime.now());
        comment.setUpdatedAt(LocalDateTime.now());
        comment.setStatus(Status.ACTIVE);

        if (dto.getReplyTo() != null && !dto.getReplyTo().isEmpty()) {
            Comment replyTo = this.commentRepository.getById(dto.getReplyTo());
            if (replyTo == null) throw new ErrorHandler(ErrorType.COMMENT_NOT_FOUND);
            comment.setReplyTo(replyTo);
        }

        Comment saved = this.commentRepository.save(comment);
        comment.setId(saved.getId());

        return CommentMapper.create().toResponse(comment);
    }

    @Override
        public ToggleCommentVotesRes toggleVotes(ToggleCommentVotesReq dto) {
        User user = this.userRepository.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        Comment comment = this.commentRepository.getById(dto.getCommentId());
        if (comment == null) throw new ErrorHandler(ErrorType.COMMENT_NOT_FOUND);

        String userId = user.getId();

        Set<String> upvoters = comment.getUpvoters();
        Set<String> downvoters = comment.getDownvoters();

        if (Vote.UPVOTE == dto.getVoteType()) {
            if (upvoters.contains(userId)) {
                upvoters.remove(userId);
            } else {
                upvoters.add(userId);
                downvoters.remove(userId);
            }
        }
        if (Vote.DOWNVOTE == dto.getVoteType()) {
            if (downvoters.contains(userId)) {
                downvoters.remove(userId);
            } else {
                downvoters.add(userId);
                upvoters.remove(userId);
            }
        }

        comment.setUpvoters(upvoters);
        comment.setDownvoters(downvoters);

        this.commentRepository.update(comment);
        return CommentMapper.toggleVotes().toResponse(comment);
    }

    @Override
    public void delete(DeleteCommentReq dto) {
        User user = this.userRepository.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        Comment comment = this.commentRepository.getById(dto.getCommentId());
        if (comment == null) throw new ErrorHandler(ErrorType.COMMENT_NOT_FOUND);

        if (comment.getStatus() == Status.DELETED){
            throw new ErrorHandler(ErrorType.COMMENT_NOT_FOUND);
        }

        boolean isAuthor = comment.getAuthor().getId().equals(user.getId());
        boolean isAdmin = user.getRoles().contains(Role.ADMIN);
        boolean isModerator = user.getRoles().contains(Role.MODERATOR);

        if (!isAuthor && !isAdmin && !isModerator) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        comment.setUpdatedAt(LocalDateTime.now());
        comment.setStatus(Status.DELETED);

        this.commentRepository.update(comment);
    }

}
