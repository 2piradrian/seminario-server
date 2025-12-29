package com.group3.events.data.datasource.users_server.repository;

import com.group3.events.config.beans.LoadBalancerConfiguration;
import com.group3.events.data.datasource.users_server.responses.AuthUserRes;
import com.group3.events.data.datasource.users_server.responses.GetByListOfIdsPageRes;
import com.group3.events.data.datasource.users_server.responses.GetUserByIdRes;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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

    @GetMapping("/api/users/get-by-list-of-ids")
    GetByListOfIdsPageRes getByListOfIds(
            @RequestHeader(value = "Authorization") String token,
            @RequestParam(value = "secret") String secret,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "size") Integer size,
            @RequestParam(value = "ids", required = false) List<String> ids
    );

}
