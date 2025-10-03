package com.group3.pages.data.datasource.profiles_server.repository;

import com.group3.pages.config.beans.LoadBalancerConfiguration;
import com.group3.pages.data.datasource.profiles_server.responses.GetUserProfileByIdRes;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "profiles-server", path = "/profiles-server")
@LoadBalancerClient(name = "profiles-server", configuration = LoadBalancerConfiguration.class)
public interface ProfilesServerRepositoryI {

    @GetMapping("/api/profiles/get-by-id/{userId}")
    GetUserProfileByIdRes getById(@PathVariable("userId") String userId);

}
