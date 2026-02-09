package com.group3.page_profiles.data.repository;

import com.group3.page_profiles.data.datasource.posts_server.repository.PostsServerRepositoryI;
import com.group3.page_profiles.domain.repository.PostsRepositoryI;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class PostsRepository implements PostsRepositoryI {

    private final PostsServerRepositoryI postsServerRepository;

    @Override
    public void deletePostsByPageId(String pageId, String secret) {
        this.postsServerRepository.deletePostsFromPage(pageId, secret);
    }
}
