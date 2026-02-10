package com.group3.events.domain.dto.event.mapper.implementation;

import com.group3.events.domain.dto.event.request.DeleteEventReq;

import java.util.Map;


public class DeleteMapper {

    public DeleteEventReq toRequest(String token, String eventId, Map<String, Object> payload) {
        return DeleteEventReq.create(
            token,
            eventId,
                (String) payload.get("reasonId")
        );
    }
}
