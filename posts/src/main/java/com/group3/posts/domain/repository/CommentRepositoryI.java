package com.group3.posts.domain.repository;

import com.group3.entity.Comment;
import com.group3.entity.PageContent;

import java.util.List;

public interface CommentRepositoryI {

    Comment getById(String commentId);

    PageContent<Comment> getByPostId(String postId, Integer page, Integer size);

    List<Comment> getRepliesComment(String commentId);

    Comment save(Comment comment);

    Comment update(Comment comment);

    void deleteById(String id);

    void deleteAllByPostId(String postId);

    void deleteAllRepliesByCommentId(String commentId);

}
