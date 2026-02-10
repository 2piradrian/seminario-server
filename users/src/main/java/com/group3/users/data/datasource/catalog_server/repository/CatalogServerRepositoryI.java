package com.group3.users.data.datasource.catalog_server.repository;

import com.group3.users.config.beans.LoadBalancerConfiguration;
import com.group3.users.data.datasource.catalog_server.responses.instrument.*;
import com.group3.users.data.datasource.catalog_server.responses.moderation_reason.*;
import com.group3.users.data.datasource.catalog_server.responses.style.*;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(name = "catalog-server", path = "/catalog-server")
@LoadBalancerClient(name = "catalog-server", configuration = LoadBalancerConfiguration.class)
public interface CatalogServerRepositoryI {

    // Styles
    @GetMapping("/api/styles/get-all")
    GetAllStyleRes getAllStyle();

    @GetMapping("/api/styles/get-by-id/{styleId}")
    GetStyleByIdRes getStyleById(
            @PathVariable("styleId") String styleId
    );

    @GetMapping("/api/styles/get-list-by-id")
    GetStyleListByIdRes getStyleListById(
            @RequestParam(value = "ids") List<String> ids
    );

    @PostMapping("/api/styles")
    CreateStyleRes createStyle(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody Map<String, Object> payload
    );

    @PutMapping("/api/styles/{styleId}")
    EditStyleRes editStyle(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable(value = "styleId") String styleId,
            @RequestBody Map<String, Object> payload
    );

    @DeleteMapping("/api/styles/{styleId}")
    void deleteStyle(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable(value = "styleId") String styleId
    );

    // Instruments
    @GetMapping("/api/instruments/get-all")
    GetAllInstrumentRes getAllInstrument();

    @GetMapping("/api/instruments/get-by-id/{instrumentId}")
    GetInstrumentByIdRes getInstrumentById(
            @PathVariable("instrumentId") String instrumentId
    );

    @GetMapping("/api/instruments/get-list-by-id")
    GetInstrumentListByIdRes getInstrumentListById(
            @RequestParam(value = "ids") List<String> ids
    );

    @PostMapping("/api/instruments")
    CreateInstrumentRes createInstrument(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody Map<String, Object> payload
    );

    @PutMapping("/api/instruments/{instrumentId}")
    EditInstrumentRes editInstrument(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable(value = "instrumentId") String instrumentId,
            @RequestBody Map<String, Object> payload
    );

    @DeleteMapping("/api/instruments/{instrumentId}")
    void deleteInstrument(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable(value = "instrumentId") String instrumentId
    );


    // Moderation Reasons
    @GetMapping("/api/moderation-reasons/get-all")
    GetAllModerationReasonRes getAllModerationReason();

    @GetMapping("/api/moderation-reasons/get-by-id/{moderationReasonId}")
    GetModerationReasonByIdRes getModerationReasonById(
            @PathVariable("moderationReasonId") String moderationReasonId
    );

    @PostMapping("/api/moderation-reasons")
    CreateModerationReasonRes createModerationReason(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody Map<String, Object> payload
    );

    @PutMapping("/api/moderation-reasons/{moderationReasonId}")
    EditModerationReasonRes editModerationReason(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable(value = "moderationReasonId") String moderationReasonId,
            @RequestBody Map<String, Object> payload
    );

    @DeleteMapping("/api/moderation-reasons/{moderationReasonId}")
    void deleteModerationReason(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable(value = "moderationReasonId") String moderationReasonId
    );

}
