package com.group3.events.data.datasource.notifications_server.repository;

import com.group3.events.config.beans.LoadBalancerConfiguration;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "notification-server", path = "/notification-server")
@LoadBalancerClient(name = "notifications-server", configuration = LoadBalancerConfiguration.class)
public interface NotificationsServerRepositoryI {

    @PostMapping("/api/notifications")
    void create(
            @RequestBody Map<String, Object> payload
    );

    @DeleteMapping("/api/notifications/delete-by-sourceId")
    void deleteBySourceId(
            @RequestHeader("Authorization") String token,
            @RequestParam("secret") String secret,
            @RequestParam("sourceId") String sourceId
    );

}
