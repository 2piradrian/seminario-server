package com.group3.page_profiles.data.datasource.events_server.repository;

import com.group3.page_profiles.config.beans.LoadBalancerConfiguration;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "events-server", path = "/events-server")
@LoadBalancerClient(name = "events-server", configuration = LoadBalancerConfiguration.class)
public interface EventsServerRepositoryI {

    @DeleteMapping("/api/events/delete-by/page/{pageId}")
    void deleteEventsFromPage(
            @PathVariable(value = "pageId") String pageId,
            @RequestParam(value = "secret") String secret
    );

}