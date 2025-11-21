package com.group3.posts.data.datasource.catalog_server.repository;

import com.group3.posts.config.beans.LoadBalancerConfiguration;
import com.group3.posts.data.datasource.catalog_server.responses.*;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(name = "catalog-server", path = "/catalog-server")
@LoadBalancerClient(name = "catalog-server", configuration = LoadBalancerConfiguration.class)
public interface CatalogServerRepositoryI {

    @GetMapping("/api/categories/get-all")
    GetAllCategoryRes getAllCategory();

    @GetMapping("/api/categories/get-by-id/{categoryId}")
    GetCategoryByIdRes getCategoryById(@PathVariable("categoryId") String styleId);

    @GetMapping("/api/categories/get-list-by-id")
    GetCategoryListByIdRes getCategoryListById(@RequestParam(value = "ids") List<String> ids);

    @GetMapping("/api/post-types/get-by-id/{postTypeId}")
    GetPostTypeByIdRes getByPostTypeId(
        @PathVariable("postTypeId") String postTypeId
    );
}
