package com.group3.events.domain.dto.event.mapper.implementation;

import com.group3.entity.Event;
import com.group3.entity.PageContent;
import com.group3.events.domain.dto.event.request.GetEventAndAssistsPageReq;
import com.group3.events.domain.dto.event.response.GetEventAndAssistsPageRes;

import java.util.Map;

public class GetEventAndAssistsMapper {

    public GetEventAndAssistsPageReq toRequest(String token, String userId, Integer page, Integer size) {
        return GetEventAndAssistsPageReq.create(
            token,
            userId,
            page,
            size
        );
    }

    public GetEventAndAssistsPageRes toResponse(PageContent<Event> events) {
        return new GetEventAndAssistsPageRes(
            events.getContent(),
            events.getNextPage()
        );
    }

}
