package com.group3.profiles.data.users_server.repository;

import com.group3.profiles.config.beans.LoadBalancerConfiguration;
import com.group3.profiles.data.users_server.responses.auth.AuthUserRes;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "users-server", path = "/users-server")
@LoadBalancerClient(name = "users-server", configuration = LoadBalancerConfiguration.class)
public interface UsersServerRepositoryI {

    @GetMapping("/api/auth")
    AuthUserRes auth(@RequestHeader(value = "Authorization") String token);

}
