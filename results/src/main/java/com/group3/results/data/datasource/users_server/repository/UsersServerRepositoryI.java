package com.group3.results.data.datasource.users_server.repository;

import com.group3.results.config.beans.LoadBalancerConfiguration;
import com.group3.results.data.datasource.users_server.responses.*;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping("/api/users/get-user-filtered")
    GetUserPageFilteredRes getUserFiltered(
        @RequestHeader(value = "Authorization") String token,
        @RequestParam(value = "secret") String secret,
        @RequestParam(value = "page") Integer page,
        @RequestParam(value = "size") Integer size,
        @RequestParam(value = "fullname", required = false) String fullname,
        @RequestParam(value = "styles", required = false) List<String> styles,
        @RequestParam(value = "instruments", required = false) List<String> instruments
    );

    @GetMapping("/api/user-profiles/get-by-id-with-following/{userId}")
    GetUserProfileWithFollowingByIdRes getByIdWithFollowing(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable("userId") String userId, @RequestBody Map<String, Object> payload
    );

    @GetMapping("/api/follows/get-all-followers/{id}")
    GetAllFollowersRes getAllFollowers(
        @RequestHeader(value = "Authorization") String token,
        @PathVariable(value = "id") String id,
        @RequestParam(value = "secret") String secret
    );

    @GetMapping("/api/users/get-by-list-of-ids")
    GetByListOfIdsPageRes getByListOfIds(
            @RequestHeader(value = "Authorization") String token,
            @RequestParam(value = "secret") String secret,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "size") Integer size,
            @RequestParam(value = "ids", required = false) List<String> ids
    );

}
