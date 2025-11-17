package com.group3.page_profiles.data.datasource.notifications_server.repository;

import com.group3.page_profiles.config.beans.LoadBalancerConfiguration;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "notifications-server", path = "/notifications-server")
@LoadBalancerClient(name = "notifications-server", configuration = LoadBalancerConfiguration.class)
public interface NotificationsServerRepositoryI {

    @PostMapping("/api/notifications/create")
    void create(@RequestBody Map<String, Object> payload);

}
