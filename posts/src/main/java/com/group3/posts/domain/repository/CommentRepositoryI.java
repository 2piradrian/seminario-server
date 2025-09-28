package com.group3.posts.domain.repository;

import com.group3.entity.Comment;
import com.group3.entity.PageContent;

public interface CommentRepositoryI {

    Comment getById(String commentId);

    PageContent getByForumId(String postId, Integer page, Integer size);

    Comment save(Comment comment);

    Comment update(Comment comment);

}
