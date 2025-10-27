package com.group3.events.data.datasource.user_profiles_server.repository;

import com.group3.events.config.beans.LoadBalancerConfiguration;
import com.group3.events.data.datasource.user_profiles_server.responses.GetUserProfileByIdRes;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "user-profiles-server", path = "/user-profiles-server")
@LoadBalancerClient(name = "user-profiles-server", configuration = LoadBalancerConfiguration.class)
public interface UserProfilesServerRepositoryI {

    @GetMapping("/api/user-profiles/get-by-id/{userId}")
    GetUserProfileByIdRes getById(@RequestHeader(value = "Authorization") String token, @PathVariable("userId") String userId);

}