package com.group3.users.data.repository;

import com.group3.users.data.datasource.posts_server.repository.PostsServerRepositoryI;
import com.group3.users.domain.repository.PostsRepositoryI;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class PostsRepository implements PostsRepositoryI {

    private final PostsServerRepositoryI repository;

    @Override
    public void delete(String token, String postId) {
        this.repository.deletePost(token, postId);
    }

}
