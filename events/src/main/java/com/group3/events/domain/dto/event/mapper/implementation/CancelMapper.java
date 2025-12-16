package com.group3.events.domain.dto.event.mapper.implementation;

import com.group3.entity.Event;
import com.group3.events.domain.dto.event.request.CancelEventReq;
import com.group3.events.domain.dto.event.response.CancelEventRes;

public class CancelMapper {

    public CancelEventReq toRequest(String token, String eventId) {
        return CancelEventReq.create(
            token,
            eventId
        );
    }

    public CancelEventRes toResponse(Event event) {
        return new CancelEventRes(
            event.getId()
        );
    }
}
