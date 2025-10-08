package com.group3.posts.data.datasource.pages_server.repository;

import com.group3.posts.config.beans.LoadBalancerConfiguration;
import com.group3.posts.data.datasource.pages_server.responses.GetPageByIdRes;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "pages-server", path = "/pages-server")
@LoadBalancerClient(name = "pages-server", configuration = LoadBalancerConfiguration.class)
public interface PagesServerRepositoryI {

    @GetMapping("/api/pages/get-by-id/{pageId}")
    GetPageByIdRes getById(@PathVariable("pageId") String pageId);

}