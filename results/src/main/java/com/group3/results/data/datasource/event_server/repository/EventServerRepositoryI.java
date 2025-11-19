package com.group3.results.data.datasource.event_server.repository;

import com.group3.results.config.beans.LoadBalancerConfiguration;
import com.group3.results.data.datasource.event_server.responses.GetFilteredEventPageRes;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "events-server", path = "/events-server")
@LoadBalancerClient(name = "events-server", configuration = LoadBalancerConfiguration.class)
public interface EventServerRepositoryI {

    @PostMapping("/api/events/get-filtered-events")
    GetFilteredEventPageRes getFilteredEvents(
            @RequestBody Map<String, Object> payload
    );

}
