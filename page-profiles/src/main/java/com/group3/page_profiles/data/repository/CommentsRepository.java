package com.group3.page_profiles.data.repository;

import com.group3.page_profiles.data.datasource.posts_server.repository.PostsServerRepositoryI; // Injecting PostsServerRepositoryI
import com.group3.page_profiles.domain.repository.CommentsRepositoryI;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class CommentsRepository implements CommentsRepositoryI {

    private final PostsServerRepositoryI postsServerRepository;

    @Override
    public void deleteCommentsByPageId(String pageId, String secret) {
        this.postsServerRepository.deleteCommentsFromPage(pageId, secret);
    }
}
