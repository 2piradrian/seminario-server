package com.group3.results.data.datasource.post_server.repository;

import com.group3.results.config.beans.LoadBalancerConfiguration;
import com.group3.results.data.datasource.post_server.responses.GetFilteredPostPageRes;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "posts-server", path = "/posts-server")
@LoadBalancerClient(name = "posts-server", configuration = LoadBalancerConfiguration.class)
public interface PostServerRepositoryI {

    @PostMapping("/api/posts/get-filtered-posts")
    GetFilteredPostPageRes getFilteredPosts(@RequestBody Map<String, Object> payload);

}
