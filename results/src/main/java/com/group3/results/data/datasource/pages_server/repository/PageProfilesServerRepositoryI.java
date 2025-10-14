package com.group3.results.data.datasource.pages_profiles.repository;

import com.group3.results.config.beans.LoadBalancerConfiguration;
import com.group3.results.data.datasource.pages_profiles.responses.GetPageProfilePageFilteredRes;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "page-profiles-server", path = "/page-profiles-server")
@LoadBalancerClient(name = "page-profiles-server", configuration = LoadBalancerConfiguration.class)
public interface PageProfilesServerRepositoryI {

    @GetMapping("/api/page-profiles/get-filtered")
    GetPageProfilePageFilteredRes getPageProfileFilteredPage(@RequestBody Map<String, Object> payload);

}
