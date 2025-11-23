package com.group3.users.data.datasource.images_server.repository;

import com.group3.users.config.beans.LoadBalancerConfiguration;
import com.group3.users.data.datasource.images_server.responses.UploadImageRes;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "images-server", path = "/images-server")
@LoadBalancerClient(name = "images-server", configuration = LoadBalancerConfiguration.class)
public interface ImagesServerRepositoryI {

    @PostMapping("/api/images")
    UploadImageRes upload(
            @RequestBody Map<String, Object> payload
    );

    @DeleteMapping("/api/images")
    void delete(
            @RequestBody Map<String, Object> payload
    );

}
