package com.group3.page_profiles.data.datasource.catalog_server.repository;

import com.group3.page_profiles.config.beans.LoadBalancerConfiguration;
import com.group3.page_profiles.data.datasource.catalog_server.responses.GetAllPageTypeRes;
import com.group3.page_profiles.data.datasource.catalog_server.responses.GetPageTypeByIdRes;
import com.group3.page_profiles.data.datasource.catalog_server.responses.GetPageTypeListByIdRes;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "catalog-server", path = "/catalog-server")
@LoadBalancerClient(name = "catalog-server", configuration = LoadBalancerConfiguration.class)
public interface CatalogServerRepositoryI {

    @GetMapping("/api/page-types/get-all")
    GetAllPageTypeRes getAll();

    @GetMapping("/api/page-types/get-by-id/{pageTypeId}")
    GetPageTypeByIdRes getById(@PathVariable("pageTypeId") String pageTypeId);

    @PostMapping("/api/page-types/get-list-by-id")
    GetPageTypeListByIdRes getListById(@RequestBody Map<String, Object> payload);

}
