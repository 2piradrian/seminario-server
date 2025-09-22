package com.group3.users.data.catalog_server.repository;

import com.group3.users.config.beans.LoadBalancerConfiguration;
import com.group3.users.data.catalog_server.responses.instrument.GetAllInstrumentRes;
import com.group3.users.data.catalog_server.responses.instrument.GetInstrumentByIdRes;
import com.group3.users.data.catalog_server.responses.instrument.GetInstrumentListByIdRes;
import com.group3.users.data.catalog_server.responses.style.GetAllStyleRes;
import com.group3.users.data.catalog_server.responses.style.GetStyleByIdRes;
import com.group3.users.data.catalog_server.responses.style.GetStyleListByIdRes;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "catalog-server")
@LoadBalancerClient(name = "catalog-server", configuration = LoadBalancerConfiguration.class)
public interface CatalogServerRepositoryI {

    @GetMapping("/style/get-all")
    GetAllStyleRes getAllStyle();

    @GetMapping("/style/get-by-id/{styleId}")
    GetStyleByIdRes getStyleById(@PathVariable("styleId") String styleId);

    @PostMapping("/style/get-list-by-id")
    GetStyleListByIdRes getStyleListById(@RequestBody Map<String, Object> payload);

    @GetMapping("/instrument/get-all")
    GetAllInstrumentRes getAllInstrument();

    @GetMapping("/instrument/get-by-id/{instrumentId}")
    GetInstrumentByIdRes getInstrumentById(@PathVariable("instrumentId") String instrumentId);

    @PostMapping("/instrument/get-list-by-id")
    GetInstrumentListByIdRes getInstrumentListById(@RequestBody Map<String, Object> payload);

}
