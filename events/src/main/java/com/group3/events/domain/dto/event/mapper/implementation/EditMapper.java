package com.group3.events.domain.dto.event.mapper.implementation;

import com.group3.entity.Event;
import com.group3.events.domain.dto.event.request.EditEventReq;
import com.group3.events.domain.dto.event.response.EditEventRes;

import java.util.Date;
import java.util.Map;

public class EditMapper {

    public EditEventReq toRequest(String token, Map<String, Object> payload) {
        return EditEventReq.create(
                token,
                (String) payload.get("eventId"),
                (String) payload.get("title"),
                (String) payload.get("content"),
                (String) payload.get("base64Image"),
                (Date) payload.get("dateInit"),
                (Date) payload.get("dateEnd")
        );
    }

    public EditEventRes toResponse(Event event) {
        return new EditEventRes(
                event.getId()
        );
    }
}
