package com.group3.users.domain.repository;

public interface PostsRepositoryI {

    void delete(String token, String postId);

    void deletePostsByUserId(String token, String userId, String secret);

}
