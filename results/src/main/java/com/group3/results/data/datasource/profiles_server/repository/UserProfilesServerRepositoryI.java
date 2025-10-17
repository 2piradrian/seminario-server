package com.group3.results.data.datasource.profiles_server.repository;

import com.group3.results.config.beans.LoadBalancerConfiguration;
import com.group3.results.data.datasource.profiles_server.responses.GetUserProfilePageFilteredRes;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

@FeignClient(name = "user-profiles-server", path = "/user-profiles-server")
@LoadBalancerClient(name = "user-profiles-server", configuration = LoadBalancerConfiguration.class)
public interface UserProfilesServerRepositoryI {

    @PostMapping("/api/user-profiles/get-user-filtered")
    GetUserProfilePageFilteredRes getUserProfileFilteredPage(@RequestHeader(value = "Authorization") String token, @RequestBody Map<String, Object> payload);

}
