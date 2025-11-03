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

    @PostMapping("/get-events-and-assists-by-id")
    public ResponseEntity<?> getEventsAndAssists(
        @RequestHeader(value = "Authorization") String token,
        @RequestBody Map<String, Object> payload
    ) {
        GetEventAndAssistsPageReq dto = EventMapper.getEventAndAssistsMapper().toRequest(token, payload);
        return ResponseEntity.ok(this.service.getEventsAndAssistsById(dto));
    }

    @PutMapping("/toggle-assist")
    public ResponseEntity<?> toggleAssist(
        @RequestHeader(value = "Authorization") String token,
        @RequestBody Map<String, Object> payload
    ) {
        ToggleAssistReq dto = EventMapper.toggleAssist().toRequest(token, payload);

        return ResponseEntity.ok(this.service.toggleAssist(dto));
    }

    @PutMapping("/edit")
    public ResponseEntity<?> edit(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody Map<String, Object> payload
    ) {
        EditEventReq dto = EventMapper.edit().toRequest(token, payload);

        return ResponseEntity.ok(this.service.edit(dto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody Map<String, Object> payload
    ) {
        DeleteEventReq dto = EventMapper.delete().toRequest(token, payload);
        this.service.delete(dto);

        return ResponseEntity.ok().build();
    }

}
