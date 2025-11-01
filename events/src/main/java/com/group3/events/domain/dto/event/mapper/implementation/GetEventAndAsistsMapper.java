package com.group3.events.domain.dto.event.mapper.implementation;

import com.group3.entity.Event;
import com.group3.entity.PageContent;
import com.group3.events.domain.dto.event.request.GetEventAndAsistsPageReq;
import com.group3.events.domain.dto.event.response.GetEventAndAsistsPageRes;

import java.util.Map;

public class GetEventAndAsistsMapper {

    public GetEventAndAsistsPageReq toRequest(String token, Map<String, Object> payload) {
        return GetEventAndAsistsPageReq.create(
            token,
            (String) payload.get("userId"),
            (Integer) payload.get("page"),
            (Integer) payload.get("size")
        );
    }

    public GetEventAndAsistsPageRes toResponse(PageContent<Event> events) {
        return new GetEventAndAsistsPageRes(
            events.getContent(),
            events.getNextPage()
        );
    }

}
