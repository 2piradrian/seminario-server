package com.group3.events.domain.repository;

import com.group3.entity.Event;
import com.group3.entity.PageContent;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface EventRepositoryI {

    Event getById(String eventId);

    Event save(Event event);

    Event update(Event event);

    PageContent<Event> getByAuthorOrAssistant(String authorId, Integer page, Integer size);

    List<Event> getInDateRange(String userId, Date dateStart, Date dateEnd);

    PageContent<Event> getFilteredEvents(Integer page, Integer size, String text, Date dateInit, Date dateEnd);

    PageContent<Event> getExpiredEvents(LocalDateTime now, Integer size);

    void updateBatch(List<Event> events);

    PageContent<Event> getInactiveEventsToActivate(LocalDateTime now, Integer size);

}
