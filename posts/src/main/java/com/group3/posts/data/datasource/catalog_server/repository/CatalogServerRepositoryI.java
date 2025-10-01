package com.group3.posts.data.datasource.catalog_server.repository;

import com.group3.posts.config.beans.LoadBalancerConfiguration;
import com.group3.posts.data.datasource.catalog_server.responses.*;
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

    @GetMapping("/api/categories/get-all")
    GetAllCategoryRes getAllCategory();

    @GetMapping("/api/categories/get-by-id/{categoryId}")
    GetCategoryByIdRes getCategoryById(@PathVariable("categoryId") String styleId);

    @PostMapping("/api/categories/get-list-by-id")
    GetCategoryListByIdRes getCategoryListById(@RequestBody Map<String, Object> payload);

    @GetMapping("/api/page-types/get-all")
    GetAllPageTypeRes getAllPageType();

    @GetMapping("/api/page-types/get-by-id/{pageTypeId}")
    GetPageTypeByIdRes getPageTypeById(@PathVariable("pageTypeId") String instrumentId);

    @PostMapping("/api/page-types/get-list-by-id")
    GetPageTypeListByIdRes getPageTypeListById(@RequestBody Map<String, Object> payload);

}
