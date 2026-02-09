package com.group3.users.data.repository;

import com.group3.users.data.datasource.posts_server.repository.PostsServerRepositoryI;
import com.group3.users.domain.repository.CommentsRepositoryI;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class CommentsRepository implements CommentsRepositoryI {

    private final PostsServerRepositoryI repository;

    @Override
    public void delete(String token, String commentId) {
        this.repository.deleteComment(token, commentId);
    }

}
