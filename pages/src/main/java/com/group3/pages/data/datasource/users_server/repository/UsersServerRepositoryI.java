package com.group3.pages.data.datasource.users_server.repository;

import com.group3.pages.config.beans.LoadBalancerConfiguration;
import com.group3.pages.data.datasource.users_server.responses.AuthUserRes;
import com.group3.pages.data.datasource.users_server.responses.GetUserByIdRes;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "users-server", path = "/users-server")
@LoadBalancerClient(name = "users-server", configuration = LoadBalancerConfiguration.class)
public interface UsersServerRepositoryI {

    @GetMapping("/api/auth/")
    AuthUserRes auth(@RequestHeader(value = "Authorization") String token);

    @GetMapping("/api/users/get-by-id/{userId}")
    GetUserByIdRes getById(@PathVariable(value = "userId") String userId);

}
