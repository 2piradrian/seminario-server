package com.group3.results.data.datasource.event_server.repository;

import com.group3.results.config.beans.LoadBalancerConfiguration;
import com.group3.results.data.datasource.event_server.responses.GetFilteredEventPageRes;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.Map;

@FeignClient(name = "events-server", path = "/events-server")
@LoadBalancerClient(name = "events-server", configuration = LoadBalancerConfiguration.class)
public interface EventServerRepositoryI {

    @GetMapping("/api/events/get-filtered-events")
    GetFilteredEventPageRes getFilteredEvents(
            @RequestParam(value = "secret") String secret,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "size") Integer size,
            @RequestParam(value = "text") String text,
            @RequestParam(value = "dateInit") Date dateInit,
            @RequestParam(value = "dateInit") Date dateEnd
    );

}
