package com.group3.events.domain.dto.event.mapper.implementation;

import com.group3.entity.Event;
import com.group3.entity.PageContent;
import com.group3.events.domain.dto.event.request.GetOwnEventPageReq;
import com.group3.events.domain.dto.event.response.GetOwnEventPageRes;

import java.util.Map;

public class GetOwnEventMapper {

    public GetOwnEventPageReq toRequest(String token, Map<String, Object> payload) {
        return GetOwnEventPageReq.create(
            token,
            (Integer) payload.get("page"),
            (Integer) payload.get("size")
        );
    }

    public GetOwnEventPageRes toResponse(PageContent<Event> events) {
        return new GetOwnEventPageRes(
            events.getContent(),
            events.getNextPage()
        );
    }

}
