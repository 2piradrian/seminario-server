package com.group3.page_profiles.data.datasource.users_server.repository;

import com.group3.page_profiles.config.beans.LoadBalancerConfiguration;
import com.group3.page_profiles.data.datasource.users_server.responses.GetFollowersByIdRes;
import com.group3.page_profiles.data.datasource.users_server.responses.AuthUserRes;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name = "users-server", path = "/users-server")
@LoadBalancerClient(name = "users-server", configuration = LoadBalancerConfiguration.class)
public interface UsersServerRepositoryI {

    @GetMapping("/api/auth/")
    AuthUserRes auth(@RequestHeader(value = "Authorization") String token);

    @PostMapping("/api/user-profiles/get-followers-by-id")
    GetFollowersByIdRes getFollowersById(@RequestBody Map<String, Object> payload);

}
