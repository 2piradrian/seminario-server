package com.group3.results.data.datasource.event_server.repository;

import com.group3.results.config.beans.LoadBalancerConfiguration;
import com.group3.results.data.datasource.event_server.responses.GetEventByProfileIdPageRes;
import com.group3.results.data.datasource.event_server.responses.GetFilteredEventPageRes;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@FeignClient(name = "events-server", path = "/events-server")
@LoadBalancerClient(name = "events-server", configuration = LoadBalancerConfiguration.class)
public interface EventServerRepositoryI {

    @GetMapping("/api/events/get-filtered-events")
    GetFilteredEventPageRes getFilteredEvents(
            @RequestHeader(value = "Authorization") String token,
            @RequestParam(value = "secret") String secret,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "size") Integer size,
            @RequestParam(value = "text", required = false) String text,
            @RequestParam(value = "dateInit", required = false) String dateInit,
            @RequestParam(value = "dateEnd", required = false) String dateEnd
    );

    @GetMapping("/api/events/get-by-profile")
    GetEventByProfileIdPageRes getEventsByProfileId(
        @RequestHeader(value = "Authorization") String token,
        @RequestParam(value = "profileId") String profileId,
        @RequestParam(value = "page") Integer page,
        @RequestParam(value = "size") Integer size
    );

}
