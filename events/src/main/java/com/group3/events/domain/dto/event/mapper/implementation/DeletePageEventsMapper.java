package com.group3.events.domain.dto.event.mapper.implementation;

import com.group3.events.domain.dto.event.request.DeletePageEventsReq;

public class DeletePageEventsMapper {

    public DeletePageEventsReq toRequest(String pageId, String secret) {
        return DeletePageEventsReq.create(pageId, secret);
    }
}
