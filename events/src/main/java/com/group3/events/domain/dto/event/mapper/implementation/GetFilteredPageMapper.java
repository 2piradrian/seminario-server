package com.group3.events.domain.dto.event.mapper.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group3.entity.Event;
import com.group3.entity.PageContent;
import com.group3.entity.Post;
import com.group3.events.domain.dto.event.request.GetFilteredEventPageReq;
import com.group3.events.domain.dto.event.response.GetFilteredEventPageRes;

import java.util.Date;
import java.util.Map;

public class GetFilteredPageMapper {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public GetFilteredEventPageReq toRequest(Integer page, Integer size, String text, String secret, String dateInit, String dateEnd) {
        return GetFilteredEventPageReq.create(
            page,
            size,
            secret,
            text,
            objectMapper.convertValue(dateInit, Date.class),
            objectMapper.convertValue(dateEnd, Date.class)
        );
    }

    public GetFilteredEventPageRes toResponse(PageContent<Event> events) {
        return new GetFilteredEventPageRes(
            events.getContent(),
            events.getNextPage()
        );
    }
}
