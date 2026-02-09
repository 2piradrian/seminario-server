package com.group3.users.data.datasource.page_profiles_server.repository;

import com.group3.users.config.beans.LoadBalancerConfiguration;
import com.group3.users.data.datasource.page_profiles_server.responses.GetPageByIdRes;
import com.group3.users.data.datasource.page_profiles_server.responses.GetPageListByIdsRes;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "page-profiles-server", path = "/page-profiles-server")
@LoadBalancerClient(name = "page-profiles-server", configuration = LoadBalancerConfiguration.class)
public interface PageProfilesServerRepositoryI {

    @GetMapping("/api/page-profiles/get-by-id/{pageId}")
    GetPageByIdRes getById(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable("pageId") String pageId
    );

    @GetMapping("/api/page-profiles/get-list-by-id")
    GetPageListByIdsRes getListByIds(
        @RequestParam(value = "pageIds") List<String> pageIds,
        @RequestParam(value = "secret") String secret
    );

    @DeleteMapping("/api/page-profiles/{pageId}")
    void delete(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable("pageId") String pageId
    );

}
