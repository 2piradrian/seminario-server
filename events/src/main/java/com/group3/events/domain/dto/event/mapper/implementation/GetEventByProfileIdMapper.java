package com.group3.events.domain.dto.event.mapper.implementation;

import com.group3.entity.Event;
import com.group3.entity.PageContent;
import com.group3.events.domain.dto.event.request.GetEventByProfileIdPageReq;
import com.group3.events.domain.dto.event.response.GetEventByProfileIdPageRes;

import java.time.LocalDateTime;

public class GetEventByProfileIdMapper {

    public GetEventByProfileIdPageReq toRequest(String token, String profileId, Integer page, Integer size) {
        return GetEventByProfileIdPageReq.create(
            token,
            profileId,
            page,
            size
        );
    }

    public GetEventByProfileIdPageRes toResponse(PageContent<Event> events) {
        return new GetEventByProfileIdPageRes(
            events.getContent(),
            events.getNextPage()
        );
    }

}
