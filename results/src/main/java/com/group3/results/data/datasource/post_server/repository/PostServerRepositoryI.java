package com.group3.results.data.datasource.post_server.repository;

import com.group3.results.config.beans.LoadBalancerConfiguration;
import com.group3.results.data.datasource.post_server.responses.GetFilteredPostPageRes;
import com.group3.results.data.datasource.post_server.responses.GetOnlyPagePostPageRes;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "posts-server", path = "/posts-server")
@LoadBalancerClient(name = "posts-server", configuration = LoadBalancerConfiguration.class)
public interface PostServerRepositoryI {

    @GetMapping("/api/posts/get-filtered-posts")
    GetFilteredPostPageRes getFilteredPosts(
        @RequestHeader(value = "Authorization") String token,
        @RequestParam(value = "page") Integer page,
        @RequestParam(value = "size") Integer size,
        @RequestParam(value = "text", required = false) String text,
        @RequestParam(value = "postTypeId", required = false) String postTypeId,
        @RequestParam(value = "secret") String secret
    );

    @GetMapping("/api/posts/get-page-posts")
    GetOnlyPagePostPageRes getOnlyPagePosts(
        @RequestHeader(value = "Authorization") String token,
        @RequestParam(value = "secret") String secret,
        @RequestParam(value = "page") Integer page,
        @RequestParam(value = "size") Integer size
    );

}
