package com.group3.catalog.presentation.controller;

import com.group3.catalog.domain.dto.moderationReason.mapper.ModerationReasonMapper;
import com.group3.catalog.domain.dto.moderationReason.request.*;
import com.group3.catalog.presentation.service.ModerationReasonService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/moderation-reasons")
public class ModerationReasonController {

    private final ModerationReasonService service;

    @GetMapping("/get-all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(this.service.getAll());
    }

    @GetMapping("/get-by-id/{moderationReasonId}")
    public ResponseEntity<?> getById(
            @PathVariable(value = "moderationReasonId") String moderationReasonId
    ){
        GetModerationReasonByIdReq dto = ModerationReasonMapper.getById().toRequest(moderationReasonId);

        return ResponseEntity.ok(this.service.getById(dto));
    }

    @PostMapping
    public ResponseEntity<?> create(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody Map<String, Object> payload
    ) {
        CreateModerationReasonReq dto = ModerationReasonMapper.create().toRequest(token, payload);

        return ResponseEntity.ok(this.service.create(dto));
    }

    @PutMapping("/{moderationReasonId}")
    public ResponseEntity<?> edit(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable(value = "moderationReasonId") String moderationReasonId,
            @RequestBody Map<String, Object> payload
    ) {
        EditModerationReasonReq dto = ModerationReasonMapper.edit().toRequest(token, moderationReasonId, payload);

        return ResponseEntity.ok(this.service.edit(dto));
    }

    @DeleteMapping("/{moderationReasonId}")
    public ResponseEntity<?> delete(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable(value = "moderationReasonId") String moderationReasonId
    ) {
        DeleteModerationReasonReq dto = ModerationReasonMapper.delete().toRequest(token, moderationReasonId);
        this.service.delete(dto);

        return ResponseEntity.ok().build();
    }

}
