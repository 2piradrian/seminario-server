package com.group3.events.domain.repository;

import com.group3.entity.Event;
import com.group3.entity.PageContent;

import java.util.Date;

public interface EventRepositoryI {

    Event getById(String eventId);

    Event save(Event event);

    Event update(Event event);

    PageContent<Event> getByAuthorOrAssistant(String authorId, Integer page, Integer size);

    PageContent<Event> getFilteredEvents(Integer page, Integer size, String text, Date dateInit, Date dateEnd);
}
