package com.group3.events.domain.dto.event.mapper.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group3.entity.Event;
import com.group3.events.domain.dto.event.request.EditEventReq;
import com.group3.events.domain.dto.event.response.EditEventRes;

import java.util.Date;
import java.util.Map;

public class EditMapper {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public EditEventReq toRequest(
        String token,
        String eventId,
        String title,
        String content,
        String base64Image,
        String dateInit,
        String dateEnd
    ) {
        return EditEventReq.create(
            token,
            eventId,
            title,
            content,
            base64Image,
            objectMapper.convertValue(dateInit, Date.class),
            objectMapper.convertValue(dateEnd, Date.class)
        );
    }

    public EditEventRes toResponse(Event event) {
        return new EditEventRes(
            event.getId()
        );
    }
}
