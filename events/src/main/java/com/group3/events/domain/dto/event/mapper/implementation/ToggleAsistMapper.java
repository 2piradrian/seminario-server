package com.group3.events.domain.dto.event.mapper.implementation;

import com.group3.entity.Event;
import com.group3.entity.Post;
import com.group3.events.domain.dto.event.request.ToggleAsistReq;
import com.group3.events.domain.dto.event.response.ToggleAsistRes;

import java.util.Map;

public class ToggleAsistMapper {

    public ToggleAsistReq toRequest(String token, Map<String, Object> payload) {
        return ToggleAsistReq.create(
                token,
                (String) payload.get("eventId")
        );
    }

    public ToggleAsistRes toResponse(Event event) {
        return new ToggleAsistRes(
            event.getAuthor(),
            event.getId(),
            event.getPageProfile(),
            event.getImageId(),
            event.getTitle(),
            event.getContent(),
            event.getViews(),
            event.getCreatedAt()
        );
    }
}
