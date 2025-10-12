package com.group3.user_profiles.data.datasource.page_profiles_server.repository;

import com.group3.user_profiles.config.beans.LoadBalancerConfiguration;
import com.group3.user_profiles.data.datasource.page_profiles_server.responses.GetPageListByIdsRes;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "page-profiles-server", path = "/page-profiles-server")
@LoadBalancerClient(name = "page-profiles-server", configuration = LoadBalancerConfiguration.class)
public interface PageProfilesServerRepositoryI {

    @PostMapping("/api/page-profiles/get-list-by-id")
    GetPageListByIdsRes getListByIds(@RequestBody Map<String, Object> payload);

}
