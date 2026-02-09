package com.group3.users.domain.repository;

public interface CommentsRepositoryI {

    void delete(String token, String commentId);

    void deleteCommentsByUserId(String token, String userId, String secret);

}
