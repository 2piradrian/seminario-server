package com.group3.events.domain.dto.event.mapper.implementation;

import com.group3.entity.Event;
import com.group3.entity.PageContent;
import com.group3.events.domain.dto.event.request.GetOnlyPageEventPageReq;
import com.group3.events.domain.dto.event.response.GetOnlyPageEventPageRes;

public class GetOnlyPageEventMapper {

    public GetOnlyPageEventPageReq toRequest(String token, String secret, Integer page, Integer size) {
        return GetOnlyPageEventPageReq.create(
            token,
            secret,
            page,
            size
        );
    }

    public GetOnlyPageEventPageRes toResponse(PageContent<Event> events) {
        return new GetOnlyPageEventPageRes(
            events.getContent(),
            events.getNextPage()
        );
    }

}
