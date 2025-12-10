package com.group3.events.domain.dto.event.mapper.implementation;

import com.group3.entity.CursorContent;
import com.group3.entity.Event;
import com.group3.events.domain.dto.event.request.GetEventByCursorPageReq;
import com.group3.events.domain.dto.event.response.GetEventByCursorPageRes;

import java.time.LocalDateTime;

public class GetEventByCursorMapper {

    public GetEventByCursorPageReq toRequest(String token, String profileId, LocalDateTime cursor, Integer size) {
        return GetEventByCursorPageReq.create(
            token,
            profileId,
            cursor,
            size
        );
    }

    public GetEventByCursorPageRes toResponse(CursorContent<Event> events) {
        return new GetEventByCursorPageRes(
            events.getContent(),
            events.getNextCursor()
        );
    }

}
