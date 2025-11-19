package com.group3.results.data.datasource.post_server.repository;

import com.group3.results.config.beans.LoadBalancerConfiguration;
import com.group3.results.data.datasource.post_server.responses.GetFilteredPostPageRes;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "posts-server", path = "/posts-server")
@LoadBalancerClient(name = "posts-server", configuration = LoadBalancerConfiguration.class)
public interface PostServerRepositoryI {

    @GetMapping("/api/posts/get-filtered-posts")
    GetFilteredPostPageRes getFilteredPosts(
        @RequestParam(value = "page") Integer page,
        @RequestParam(value = "size") Integer size,
        @RequestParam(value = "text", required = false) String text,
        @RequestParam(value = "postTypeId", required = false) String postTypeId,
        @RequestParam(value = "secret") String secret
    );

}
