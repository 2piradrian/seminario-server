package com.group3.page_profiles.data.datasource.users_server.repository;

import com.group3.page_profiles.config.beans.LoadBalancerConfiguration;
import com.group3.page_profiles.data.datasource.users_server.responses.GetAllFollowersRes;
import com.group3.page_profiles.data.datasource.users_server.responses.GetFollowersByIdRes;
import com.group3.page_profiles.data.datasource.users_server.responses.AuthUserRes;
import com.group3.page_profiles.data.datasource.users_server.responses.GetUserByIdRes;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name = "users-server", path = "/users-server")
@LoadBalancerClient(name = "users-server", configuration = LoadBalancerConfiguration.class)
public interface UsersServerRepositoryI {

    @GetMapping("/api/auth")
    AuthUserRes auth(
            @RequestHeader(value = "Authorization") String token
    );

    @GetMapping("/api/users/get-by-id/{userId}")
    GetUserByIdRes getById(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable(value = "userId") String userId
    );

    @GetMapping("/api/follows/get-followers-by-id/{id}")
    GetFollowersByIdRes getFollowersById(
        @PathVariable(value = "id") String id,
        @RequestParam(value = "secret") String secret
    );

    @GetMapping("/api/follows/get-all-followers/{id}")
    GetAllFollowersRes getAllFollowers(
        @PathVariable(value = "id") String id,
        @RequestParam(value = "secret") String secret
    );

}
