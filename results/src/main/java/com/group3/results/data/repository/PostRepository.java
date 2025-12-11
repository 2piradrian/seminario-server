package com.group3.results.data.repository;

import com.group3.entity.Post;
import com.group3.results.data.datasource.post_server.repository.PostServerRepositoryI;
import com.group3.results.data.datasource.post_server.responses.GetFilteredPostPageRes;
import com.group3.results.data.datasource.post_server.responses.GetOnlyPagePostPageRes;
import com.group3.results.domain.repository.PostRepositoryI;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class PostRepository implements PostRepositoryI {

    private final PostServerRepositoryI repository;

    @Override
    public List<Post> getFilteredPosts(String token, Integer page, Integer size, String text, String postTypeId, String secret) {

        GetFilteredPostPageRes response = this.repository.getFilteredPosts(token, page, size, text, postTypeId, secret);

        return response.getPosts();
    }

    @Override
    public List<Post> getOnlyPagePosts(String token, Integer page, Integer size) {

        GetOnlyPagePostPageRes response = this.repository.getOnlyPagePosts(
            token, page, size
        );

        return response.getPosts();
    }

}
