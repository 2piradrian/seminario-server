package com.group3.users.data.profiles_server.repository;

import com.group3.users.config.beans.LoadBalancerConfiguration;
import com.group3.users.data.profiles_server.responses.GetOwnUserProfileRes;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

@FeignClient(name = "profile-server", path = "/profile-server")
@LoadBalancerClient(name = "profile-server", configuration = LoadBalancerConfiguration.class)
public interface ProfileServerRepositoryI {

    @PostMapping("/api/create")
    void create(@RequestBody Map<String, Object> payload);

    @PostMapping("/api/get-own-profile")
    GetOwnUserProfileRes getOwnProfile(@RequestHeader(value = "Authorization") String token);

}
