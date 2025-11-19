package com.group3.events.domain.dto.event.mapper.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group3.entity.Event;
import com.group3.events.domain.dto.event.request.EditEventReq;
import com.group3.events.domain.dto.event.response.EditEventRes;

import java.util.Date;
import java.util.Map;

public class EditMapper {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public EditEventReq toRequest(String token, String eventId, Map<String, Object> payload) {
        return EditEventReq.create(
                token,
                eventId,
                (String) payload.get("title"),
                (String) payload.get("content"),
                (String) payload.get("base64Image"),
                objectMapper.convertValue(payload.get("dateInit"), Date.class),
                objectMapper.convertValue(payload.get("dateEnd"), Date.class)
        );
    }

    public EditEventRes toResponse(Event event) {
        return new EditEventRes(
                event.getId()
        );
    }
}
