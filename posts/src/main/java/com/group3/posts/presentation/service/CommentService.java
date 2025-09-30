package com.group3.posts.presentation.service;

import com.group3.entity.*;
import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import com.group3.posts.data.repository.CommentRepository;
import com.group3.posts.data.repository.PostRepository;
import com.group3.posts.data.repository.UserRepository;
import com.group3.posts.domain.dto.comment.mapper.CommentMapper;
import com.group3.posts.domain.dto.comment.request.*;
import com.group3.posts.domain.dto.comment.response.CreateCommentRes;
import com.group3.posts.domain.dto.comment.response.GetCommentPageRes;

import java.time.LocalDateTime;
import java.util.Set;

public class CommentService implements CommentServiceI {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    public GetCommentPageRes getComments(GetCommentPageReq dto) {
        Post post = this.postRepository.getById(dto.getPostId());
        if (post == null) throw new ErrorHandler(ErrorType.POST_NOT_FOUND);

        if (post.getStatus() == Status.DELETED) {
            throw new ErrorHandler(ErrorType.POST_NOT_FOUND);
        }

        PageContent<Comment> comments =
                this.commentRepository.getByPostId(dto.getPostId(), dto.getPage(), dto.getSize());

        return CommentMapper.getPage().toResponse(comments);
    }

    @Override
    public CreateCommentRes create(CreateCommentReq dto) {
        User user = this.userRepository.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        Post post = this.postRepository.getById(dto.getPostId());
        if (post == null) throw new ErrorHandler(ErrorType.POST_NOT_FOUND);

        if (post.getStatus() != Status.ACTIVE) {
            throw new ErrorHandler(ErrorType.POST_NOT_ACTIVE);
        }

        Comment comment = new Comment();

        comment.setAuthorId(user.getId());
        comment.setPostId(post.getId());
        comment.setContent(dto.getContent());
        comment.setUpvoters(Set.of());
        comment.setDownvoters(Set.of());
        comment.setCreatedAt(LocalDateTime.now());
        comment.setUpdatedAt(LocalDateTime.now());
        comment.setStatus(Status.ACTIVE);

        if (dto.getReplyTo() != null) {
            Comment replyTo = this.commentRepository.getById(dto.getReplyTo());
            if (replyTo == null) throw new ErrorHandler(ErrorType.COMMENT_NOT_FOUND);
            comment.setReplyTo(replyTo);
        }

        Comment saved = this.commentRepository.save(comment);

        return CommentMapper.create().toResponse(saved);
    }

    @Override
    public void toggleVotes(ToggleCommentVotesReq dto) {
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
    }

    @Override
    public void delete(DeleteCommentReq dto) {
        User user = this.userRepository.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        Comment comment = this.commentRepository.getById(dto.getCommentId());
        if (comment == null) throw new ErrorHandler(ErrorType.COMMENT_NOT_FOUND);

        boolean isAuthor = comment.getAuthorId().equals(user.getId());
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