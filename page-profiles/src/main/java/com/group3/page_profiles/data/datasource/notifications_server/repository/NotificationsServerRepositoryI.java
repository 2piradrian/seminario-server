package com.group3.page_profiles.data.datasource.notifications_server.repository;

import com.group3.page_profiles.config.beans.LoadBalancerConfiguration;
import com.group3.page_profiles.data.datasource.notifications_server.responses.GetLatestUncheckNotificationRes;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name = "notification-server", path = "/notification-server")
@LoadBalancerClient(name = "notifications-server", configuration = LoadBalancerConfiguration.class)
public interface NotificationsServerRepositoryI {

    @PostMapping("/api/notifications")
    void create(
            @RequestBody Map<String, Object> payload
    );

    @GetMapping("/api/notifications/get-latest-uncheck")
    GetLatestUncheckNotificationRes getLatestUncheckNotification(
            @RequestHeader(value = "Authorization") String token,
            @RequestParam(value = "secret") String secret,
            @RequestParam(value = "targetId") String targetId,
            @RequestParam(value = "sourceId") String sourceId
    );

    @PutMapping("/api/notifications/check-invitation/{notificationId}")
    void checkInvitation(
            @RequestHeader(value = "Authorization") String token,
            @RequestParam(value = "secret") String secret,
            @PathVariable(value = "notificationId") String notificationId
    );


}
