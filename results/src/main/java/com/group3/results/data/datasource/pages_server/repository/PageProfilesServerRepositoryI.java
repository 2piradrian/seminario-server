package com.group3.results.data.datasource.pages_server.repository;

import com.group3.results.config.beans.LoadBalancerConfiguration;
import com.group3.results.data.datasource.pages_server.responses.GetPageByIdRes;
import com.group3.results.data.datasource.pages_server.responses.GetPageProfilePageFilteredRes;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name = "page-profiles-server", path = "/page-profiles-server")
@LoadBalancerClient(name = "page-profiles-server", configuration = LoadBalancerConfiguration.class)
public interface PageProfilesServerRepositoryI {

    @GetMapping("/api/page-profiles/get-page-filtered")
    GetPageProfilePageFilteredRes getPageProfileFilteredPage(
        @RequestHeader(value = "Authorization") String token,
        @RequestParam(value = "secret") String secret,
        @RequestParam(value = "page") Integer page,
        @RequestParam(value = "size") Integer size,
        @RequestParam(value = "name", required = false) String name,
        @RequestParam(value = "pageTypeId", required = false) String pageTypeId
    );

    @GetMapping("/api/page-profiles/get-by-id/{pageId}")
    GetPageByIdRes getById(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable(value = "pageId") String pageId
    );

}
