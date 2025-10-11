package com.group3.results.data.datasource.profiles_server.repository;

import com.group3.results.config.beans.LoadBalancerConfiguration;
import com.group3.results.data.datasource.profiles_server.responses.GetUserProfilePageByFullnameRes;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "profiles-server", path = "/profiles-server")
@LoadBalancerClient(name = "profiles-server", configuration = LoadBalancerConfiguration.class)
public interface ProfilesServerRepositoryI {

    @GetMapping("/api/profiles/get-by-fullname")
    GetUserProfilePageByFullnameRes findByFullNameLike(@RequestBody Map<String, Object> payload);

}
