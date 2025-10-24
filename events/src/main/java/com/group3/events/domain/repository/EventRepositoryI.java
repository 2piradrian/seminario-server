package com.group3.events.domain.repository;

import com.group3.entity.Event;

public interface EventRepositoryI {

    Event getById(String eventId);

    Event save(Event event);

    Event update(Event event);

}
