package com.group3.events.domain.repository;

import com.group3.entity.Event;
import com.group3.entity.PageContent;

public interface EventRepositoryI {

    Event getById(String eventId);

    Event save(Event event);

    Event update(Event event);

    PageContent<Event> getEventsByAuthorId(String userId, Integer page, Integer size);

    PageContent<Event> getEventsByAssistant(String userId, Integer page, Integer size);

}
