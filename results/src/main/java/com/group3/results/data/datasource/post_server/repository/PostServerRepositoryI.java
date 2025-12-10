package com.group3.results.data.datasource.post_server.repository;

import com.group3.results.config.beans.LoadBalancerConfiguration;
import com.group3.results.data.datasource.post_server.responses.GetFilteredPostPageRes;
import com.group3.results.data.datasource.post_server.responses.GetPostPageByProfileRes;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

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

    @GetMapping("/api/posts/get-by-profile")
    GetPostPageByProfileRes getPostByCursor(
        @RequestHeader(value = "Authorization") String token,
        @RequestParam(value = "profileId") String profileId,
        @RequestParam(value = "page") Integer page,
        @RequestParam(value = "size") Integer size
    );

}
