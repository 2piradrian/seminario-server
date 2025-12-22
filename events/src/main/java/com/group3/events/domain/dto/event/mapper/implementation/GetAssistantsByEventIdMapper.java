package com.group3.events.domain.dto.event.mapper.implementation;

import com.group3.entity.PageContent;
import com.group3.entity.User;
import org.springframework.stereotype.Component;

@Component
public class GetAssistantsByEventIdMapper {

    public GetAssistantsByEventIdReq toRequest(
            String token,
            String eventId,
            Integer page,
            Integer size
    ) {
        return GetAssistantsByEventIdReq.create(
                token,
                eventId,
                page,
                size
        );
    }

    public GetAssistantsByEventIdRes toResponse(PageContent<User> assistants) {
        return new GetAssistantsByEventIdRes(
                assistants.getContent(),
                assistants.getNextPage()
        );
    }
}
