package com.group3.events.presentation.controller;

import com.group3.events.domain.dto.event.mapper.EventMapper;
import com.group3.events.domain.dto.event.request.*;
import com.group3.events.presentation.service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/events")
public class EventController {

    private final EventService service;

    @PostMapping("/create")
    public ResponseEntity<?> create(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody Map<String, Object> payload
    ) {
        CreateEventReq dto = EventMapper.create().toRequest(token, payload);

        return ResponseEntity.ok(this.service.create(dto));
    }

    @GetMapping("/get-by-id/{eventId}")
    public ResponseEntity<?> getById(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable(value = "eventId") String eventId
    ) {
        GetEventByIdReq dto = EventMapper.getById().toRequest(eventId, token);

        return ResponseEntity.ok(this.service.getById(dto));
    }

    @PostMapping("/get-events-and-asists-by-id")
    public ResponseEntity<?> getOwnEvents(
        @RequestHeader(value = "Authorization") String token,
        @RequestBody Map<String, Object> payload
    ) {
        GetEventAndAsistsPageReq dto = EventMapper.getEventAndAsistsMapper().toRequest(token, payload);
        return ResponseEntity.ok(this.service.getEventsAndAsistsById(dto));
    }

    @PutMapping("/toggle-asist")
    public ResponseEntity<?> toggleAsist(
        @RequestHeader(value = "Authorization") String token,
        @RequestBody Map<String, Object> payload
    ) {
        ToggleAsistReq dto = EventMapper.toggleAsist().toRequest(token, payload);

        return ResponseEntity.ok(this.service.toggleAsist(dto));
    }

    @PutMapping("/edit")
    public ResponseEntity<?> edit(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody Map<String, Object> payload
    ) {
        EditEventReq dto = EventMapper.edit().toRequest(token, payload);

        return ResponseEntity.ok(this.service.edit(dto));
    }

}
