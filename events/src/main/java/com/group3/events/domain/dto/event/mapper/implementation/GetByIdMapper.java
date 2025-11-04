package com.group3.events.domain.dto.event.mapper.implementation;

import com.group3.entity.Event;
import com.group3.events.domain.dto.event.request.GetEventByIdReq;
import com.group3.events.domain.dto.event.response.GetEventByIdRes;

public class GetByIdMapper {

    public GetEventByIdReq toRequest(String eventId, String token) {
        return GetEventByIdReq.create(
            eventId,
            token
        );
    }

    public GetEventByIdRes toResponse(Event event) {
        return new GetEventByIdRes(
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
            event.getCreatedAt()
        );
    }

}
