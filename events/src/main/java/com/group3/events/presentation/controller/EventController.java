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

    @PostMapping
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

    @GetMapping("/get-filtered-events")
    public ResponseEntity<?> getFilteredEvents(
        @RequestHeader(value = "Authorization") String token,
        @RequestParam(value = "secret") String secret,
        @RequestParam(value = "page") Integer page,
        @RequestParam(value = "size") Integer size,
        @RequestParam(value = "text", required = false) String text,
        @RequestParam(value = "dateInit", required = false) String dateInit,
        @RequestParam(value = "dateEnd", required = false) String dateEnd
    ) {
        GetFilteredEventPageReq dto = EventMapper.getFilteredPage().toRequest(token, page, size, text, secret, dateInit, dateEnd);

        return ResponseEntity.ok(this.service.getFilteredEvents(dto));
    }

    @GetMapping("/get-events-and-assists-by-id")
    public ResponseEntity<?> getEventsAndAssists(
        @RequestHeader(value = "Authorization") String token,
        @RequestParam(value = "userId") String userId,
        @RequestParam(value = "page") Integer page,
        @RequestParam(value = "size") Integer size
    ) {
        GetEventAndAssistsPageReq dto = EventMapper.getEventAndAssistsMapper().toRequest(token, userId, page, size);
        return ResponseEntity.ok(this.service.getEventsAndAssistsById(dto));
    }

    @GetMapping("/get-page-events")
    public ResponseEntity<?> getPageEvents(
        @RequestHeader(value = "Authorization") String token,
        @RequestParam(value = "page") Integer page,
        @RequestParam(value = "size") Integer size
    ) {
        GetOnlyPageEventPageReq dto = EventMapper.getOnlyPageEvents().toRequest(token, page, size);

        return ResponseEntity.ok(this.service.getPageOnlyEventsPage(dto));
    }

    @GetMapping("/get-events-by-date-range")
    public ResponseEntity<?> getEventsByDateRange(
        @RequestHeader(value = "Authorization") String token,
        @RequestParam(value = "userId") String userId,
        @RequestParam(value = "dateMonth") String dateMonth
    ) {
        GetEventByDateRangeReq dto = EventMapper.getEventByDateRange().toRequest(token, userId, dateMonth);
        return ResponseEntity.ok(this.service.getEventsByDateRange(dto));
    }

    @PutMapping("/toggle-assist")
    public ResponseEntity<?> toggleAssist(
        @RequestHeader(value = "Authorization") String token,
        @RequestBody Map<String, Object> payload
    ) {
        ToggleAssistReq dto = EventMapper.toggleAssist().toRequest(token, payload);

        return ResponseEntity.ok(this.service.toggleAssist(dto));
    }

    @PutMapping("/{eventId}")
    public ResponseEntity<?> edit(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable(value = "eventId") String eventId,
            @RequestBody Map<String, Object> payload
    ) {
        EditEventReq dto = EventMapper.edit().toRequest(token, eventId, payload);

        return ResponseEntity.ok(this.service.edit(dto));
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<?> delete(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable(value = "eventId") String eventId
    ) {
        DeleteEventReq dto = EventMapper.delete().toRequest(token, eventId);
        this.service.delete(dto);

        return ResponseEntity.ok().build();
    }

}
