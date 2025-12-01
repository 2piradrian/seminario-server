package com.group3.events.domain.dto.event.mapper.implementation;

import com.group3.entity.Event;
import com.group3.events.domain.dto.event.request.ToggleAssistReq;
import com.group3.events.domain.dto.event.response.ToggleAssistRes;

import java.util.Map;

public class ToggleAssistMapper {

    public ToggleAssistReq toRequest(String token, Map<String, Object> payload) {
        return ToggleAssistReq.create(
                token,
                (String) payload.get("eventId")
        );
    }

    public ToggleAssistRes toResponse(Event event) {
        return new ToggleAssistRes(
            event.getAuthor(),
            event.getId(),
            event.getPageProfile(),
            event.getImageId(),
            event.getTitle(),
            event.getContent(),
            event.getViews(),
            event.getAssistsQuantity(),
            event.getDateInit(),
            event.getDateEnd(),
            event.getStatus(),
            event.getCreatedAt(),
            event.getIsAssisting()
        );
    }
}
