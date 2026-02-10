package com.group3.notifications.data.datasource.catalog_server.repository;

import com.group3.notifications.config.beans.LoadBalancerConfiguration;
import com.group3.notifications.data.datasource.catalog_server.responses.moderation_reason.CreateModerationReasonRes;
import com.group3.notifications.data.datasource.catalog_server.responses.moderation_reason.EditModerationReasonRes;
import com.group3.notifications.data.datasource.catalog_server.responses.moderation_reason.GetAllModerationReasonRes;
import com.group3.notifications.data.datasource.catalog_server.responses.moderation_reason.GetModerationReasonByIdRes;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name = "catalog-server", path = "/catalog-server")
@LoadBalancerClient(name = "catalog-server", configuration = LoadBalancerConfiguration.class)
public interface CatalogServerRepositoryI {

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
