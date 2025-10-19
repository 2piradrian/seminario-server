package com.group3.results.data.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.group3.entity.PageContent;
import com.group3.entity.Post;
import com.group3.entity.Status;
import com.group3.entity.User;
import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import com.group3.results.data.datasource.pages_server.repository.PageProfilesServerRepositoryI;
import com.group3.results.data.datasource.pages_server.responses.GetPageProfilePageFilteredRes;
import com.group3.results.data.datasource.post_server.repository.PostServerRepositoryI;
import com.group3.results.data.datasource.post_server.responses.GetFilteredPostPageRes;
import com.group3.results.domain.repository.PostRepositoryI;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class PostRepository implements PostRepositoryI {

    private final PostServerRepositoryI repository;

    @Override
    public List<Post> getFilteredPosts(Integer page, Integer size, String text, String secret) {
        Map<String,Object> payload = new HashMap<>();

        payload.put("page", page);
        payload.put("size", size);
        payload.put("text", text);
        payload.put("secret", secret);

        GetFilteredPostPageRes response = this.repository.getFilteredPosts(payload);

        return response.getPosts();
    }

}
