package com.group3.events.domain.dto.event.mapper.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group3.entity.Event;
import com.group3.events.domain.dto.event.request.GetEventByDateRangeReq;
import com.group3.events.domain.dto.event.request.GetFilteredEventPageReq;
import com.group3.events.domain.dto.event.response.GetEventByDateRangeRes;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class GetEventByDateRangeMapper {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public GetEventByDateRangeReq toRequest(
        String token,
        String userId,
        String dateMonth
    ) {
        return GetEventByDateRangeReq.create(
            token,
            userId,
            objectMapper.convertValue(dateMonth, Date.class)
        );
    }

    public GetEventByDateRangeRes toResponse(List<Event> events) {
        return new GetEventByDateRangeRes(events);
    }

}
