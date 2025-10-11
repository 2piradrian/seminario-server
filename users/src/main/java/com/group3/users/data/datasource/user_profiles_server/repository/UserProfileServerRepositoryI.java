package com.group3.users.data.datasource.user_profiles_server.repository;

import com.group3.users.config.beans.LoadBalancerConfiguration;
import com.group3.users.data.datasource.user_profiles_server.responses.GetOwnUserProfileRes;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

@FeignClient(name = "user-profiles-server", path = "/user-profiles-server")
@LoadBalancerClient(name = "user-profiles-server", configuration = LoadBalancerConfiguration.class)
public interface UserProfileServerRepositoryI {

    @PostMapping("/api/user-profiles/create")
    void create(@RequestBody Map<String, Object> payload);

    @PostMapping("/api/user-profiles/get-own-profile")
    GetOwnUserProfileRes getOwnProfile(@RequestHeader(value = "Authorization") String token);

}
