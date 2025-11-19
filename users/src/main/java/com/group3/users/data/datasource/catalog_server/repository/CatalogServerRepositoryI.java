package com.group3.users.data.datasource.catalog_server.repository;

import com.group3.users.config.beans.LoadBalancerConfiguration;
import com.group3.users.data.datasource.catalog_server.responses.instrument.GetAllInstrumentRes;
import com.group3.users.data.datasource.catalog_server.responses.instrument.GetInstrumentByIdRes;
import com.group3.users.data.datasource.catalog_server.responses.instrument.GetInstrumentListByIdRes;
import com.group3.users.data.datasource.catalog_server.responses.style.GetAllStyleRes;
import com.group3.users.data.datasource.catalog_server.responses.style.GetStyleByIdRes;
import com.group3.users.data.datasource.catalog_server.responses.style.GetStyleListByIdRes;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(name = "catalog-server", path = "/catalog-server")
@LoadBalancerClient(name = "catalog-server", configuration = LoadBalancerConfiguration.class)
public interface CatalogServerRepositoryI {

    @GetMapping("/api/styles/get-all")
    GetAllStyleRes getAllStyle();

    @GetMapping("/api/styles/get-by-id/{styleId}")
    GetStyleByIdRes getStyleById(@PathVariable("styleId") String styleId);

    @GetMapping("/api/styles/get-list-by-id")
    GetStyleListByIdRes getStyleListById(@RequestParam(value = "ids") List<String> ids);

    @GetMapping("/api/instruments/get-all")
    GetAllInstrumentRes getAllInstrument();

    @GetMapping("/api/instruments/get-by-id/{instrumentId}")
    GetInstrumentByIdRes getInstrumentById(@PathVariable("instrumentId") String instrumentId);

    @GetMapping("/api/instruments/get-list-by-id")
    GetInstrumentListByIdRes getInstrumentListById(@RequestParam(value = "ids") List<String> ids);

}
