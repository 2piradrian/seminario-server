package com.group3.posts.data.auth_server;

import com.group3.entity.TokenClaims;
import com.group3.entity.User;
import com.group3.posts.config.beans.LoadBalancerConfiguration;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Optional;

@FeignClient(name = "users-server", path = "/users-server")
@LoadBalancerClient(name = "users-server", configuration = LoadBalancerConfiguration.class)
public interface AuthServerRepositoryI {

    @GetMapping("/api/auth")
    Optional<TokenClaims> auth (@RequestHeader("Authorization") String token);

    @GetMapping("/api/users/get-by-id/{userId]")
    Optional<User> getById(@PathVariable String userId);

}
