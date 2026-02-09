package com.group3.users.data.datasource.posts_server.repository;

import com.group3.users.config.beans.LoadBalancerConfiguration;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "posts-server", path = "/posts-server")
@LoadBalancerClient(name = "posts-server", configuration = LoadBalancerConfiguration.class)
public interface PostsServerRepositoryI {

    @DeleteMapping("/api/posts/{postId}")
    void deletePost(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable("postId") String postId
    );

    @DeleteMapping("/api/comments/{commentId}")
    void deleteComment(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable("commentId") String commentId
    );

}
