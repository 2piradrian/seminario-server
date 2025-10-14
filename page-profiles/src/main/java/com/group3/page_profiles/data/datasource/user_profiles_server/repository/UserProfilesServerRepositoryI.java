package com.group3.page_profiles.data.datasource.user_profiles_server.repository;

import com.group3.page_profiles.config.beans.LoadBalancerConfiguration;
import com.group3.page_profiles.data.datasource.user_profiles_server.responses.GetFollowersByIdRes;
import com.group3.page_profiles.data.datasource.user_profiles_server.responses.GetUserProfileByIdRes;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name = "user-profiles-server", path = "/user-profiles-server")
@LoadBalancerClient(name = "user-profiles-server", configuration = LoadBalancerConfiguration.class)
public interface UserProfilesServerRepositoryI {

    @GetMapping("/api/user-profiles/get-by-id/{userId}")
    GetUserProfileByIdRes getById(@RequestHeader(value = "Authorization") String token, @PathVariable("userId") String userId);

    @PostMapping("/get-followers-by-id")
    GetFollowersByIdRes getFollowersById(@RequestBody Map<String, Object> payload);

}
