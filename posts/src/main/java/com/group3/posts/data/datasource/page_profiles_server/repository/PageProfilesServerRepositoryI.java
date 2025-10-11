package com.group3.posts.data.datasource.page_profiles_server.repository;

import com.group3.posts.config.beans.LoadBalancerConfiguration;
import com.group3.posts.data.datasource.page_profiles_server.responses.GetPageByIdRes;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "page-profiles-server", path = "/page-profiles-server")
@LoadBalancerClient(name = "page-profiles-server", configuration = LoadBalancerConfiguration.class)
public interface PageProfilesServerRepositoryI {

    @GetMapping("/api/pages/get-by-id/{pageId}")
    GetPageByIdRes getById(@PathVariable("pageId") String pageId);

}