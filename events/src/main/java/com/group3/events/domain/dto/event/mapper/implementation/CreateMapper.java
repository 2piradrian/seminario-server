package com.group3.events.domain.dto.event.mapper.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group3.entity.Event;
import com.group3.events.domain.dto.event.request.CreateEventReq;
import com.group3.events.domain.dto.event.response.CreateEventRes;

import java.util.Date;
import java.util.Map;

public class CreateMapper {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public CreateEventReq toRequest(String token, Map<String, Object> payload) {
        return CreateEventReq.create(
                token,
                (String) payload.get("title"),
                (String) payload.get("content"),
                (String) payload.get("profileId"),
                (String) payload.get("image"),
                objectMapper.convertValue(payload.get("dateInit"), Date.class),
                objectMapper.convertValue(payload.get("dateEnd"), Date.class)
        );
    }

    public CreateEventRes toResponse(Event event) {
        return new CreateEventRes(
                event.getId()
        );
    }

}
