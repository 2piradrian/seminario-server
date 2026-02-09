package com.group3.users.data.datasource.events_server.repository;

import com.group3.users.config.beans.LoadBalancerConfiguration;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "events-server", path = "/events-server")
@LoadBalancerClient(name = "events-server", configuration = LoadBalancerConfiguration.class)
public interface EventsServerRepositoryI {

    @DeleteMapping("/api/events/{eventId}")
    void delete(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable("eventId") String eventId
    );

    @DeleteMapping("/api/events/delete-by/user/{userId}")
    void deleteByUserId(
            @RequestParam(value = "secret") String secret,
            @PathVariable("userId") String userId
    );

    @DeleteMapping("/api/events/delete-by/page/{pageProfileId}")
    void deleteByPageProfileId(
            @RequestParam(value = "secret") String secret,
            @PathVariable("pageProfileId") String pageProfileId
    );

}
