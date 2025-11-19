package com.group3.events.domain.dto.event.mapper.implementation;

import com.group3.events.domain.dto.event.request.DeleteEventReq;

import java.util.Map;

public class DeleteMapper {

    public DeleteEventReq toRequest(String token, String eventId) {
        return DeleteEventReq.create(
            token,
            eventId
        );
    }
}
