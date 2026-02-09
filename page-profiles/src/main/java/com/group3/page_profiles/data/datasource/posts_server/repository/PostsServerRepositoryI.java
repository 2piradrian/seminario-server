package com.group3.page_profiles.data.datasource.posts_server.repository;

import com.group3.page_profiles.config.beans.LoadBalancerConfiguration;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "posts-server", path = "/posts-server")
@LoadBalancerClient(name = "posts-server", configuration = LoadBalancerConfiguration.class)
public interface PostsServerRepositoryI {

    @DeleteMapping("/api/posts/delete-by/page/{pageId}")
    void deletePostsFromPage(
            @PathVariable(value = "pageId") String pageId,
            @RequestParam(value = "secret") String secret
    );

    @DeleteMapping("/api/comments/delete-by/page/{pageId}")
    void deleteCommentsFromPage(
            @PathVariable(value = "pageId") String pageId,
            @RequestParam(value = "secret") String secret
    );
}