package com.group3.events.domain.dto.event.mapper.implementation;

import com.group3.entity.Event;
import com.group3.entity.PageContent;
import com.group3.events.domain.dto.event.request.GetOwnEventsAssistedPageReq;
import com.group3.events.domain.dto.event.response.GetOwnEventsAssistedPageRes;

import java.util.Map;

public class GetOwnAssistsMapper {

    public GetOwnEventsAssistedPageReq toRequest(String token, Map<String, Object> payload) {
        return GetOwnEventsAssistedPageReq.create(
                token,
                (Integer) payload.get("page"),
                (Integer) payload.get("size")
        );
    }

    public GetOwnEventsAssistedPageRes toResponse(PageContent<Event> events) {
        return new GetOwnEventsAssistedPageRes(
                events.getContent(),
                events.getNextPage()
        );
    }

}
