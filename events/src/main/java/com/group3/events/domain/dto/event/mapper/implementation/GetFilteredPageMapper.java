package com.group3.events.domain.dto.event.mapper.implementation;

import com.group3.entity.Event;
import com.group3.entity.PageContent;
import com.group3.events.domain.dto.event.request.GetFilteredEventPageReq;
import com.group3.events.domain.dto.event.response.GetFilteredEventPageRes;

import java.util.Date;

public class GetFilteredPageMapper {

    public GetFilteredEventPageReq toRequest(Integer page, Integer size, String text, String secret, Date dateInit, Date dateEnd) {
        return GetFilteredEventPageReq.create(
            page,
            size,
            text,
            secret,
            dateInit,
            dateEnd
        );
    }

    public GetFilteredEventPageRes toResponse(PageContent<Event> events) {
        return new GetFilteredEventPageRes(
            events.getContent(),
            events.getNextPage()
        );
    }
}
