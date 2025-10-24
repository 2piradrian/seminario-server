package com.group3.events.data.repository;

import com.group3.entity.Event;
import com.group3.entity.Status;
import com.group3.events.data.datasource.mapper.EventEntityMapper;
import com.group3.events.data.datasource.model.EventModel;
import com.group3.events.data.datasource.repository.PostgresEventRepositoryI;
import com.group3.events.domain.repository.EventRepositoryI;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class EventRepository implements EventRepositoryI {

    private final PostgresEventRepositoryI repository;

    @Override
    public Event getById(String eventId) {
        EventModel eventModel = this.repository.findById(eventId).orElse(null);

        if (eventModel == null) return null;
        if (!eventModel.getStatus().equals(Status.ACTIVE)) return null;

        return EventEntityMapper.toDomain(eventModel);
    }

    @Override
    public Event save(Event event) {
        EventModel eventModel = EventEntityMapper.toModel(event);
        EventModel saved = this.repository.save(eventModel);

        return EventEntityMapper.toDomain(saved);
    }

    @Override
    public Event update(Event event) {
        EventModel eventModel = EventEntityMapper.toModel(event);
        EventModel updated = this.repository.save(eventModel);

        return EventEntityMapper.toDomain(updated);
    }
}
