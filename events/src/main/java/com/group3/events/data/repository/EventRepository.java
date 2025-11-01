package com.group3.events.data.repository;

import com.group3.entity.Event;
import com.group3.entity.PageContent;
import com.group3.entity.Status;
import com.group3.events.data.datasource.postgres.mapper.EventEntityMapper;
import com.group3.events.data.datasource.postgres.model.EventModel;
import com.group3.events.data.datasource.postgres.repository.PostgresEventRepositoryI;
import com.group3.events.domain.repository.EventRepositoryI;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class EventRepository implements EventRepositoryI {

    private final PostgresEventRepositoryI repository;

    private int normalizePage(Integer page) {
        return (page != null && page > 0) ? page - 1 : 0;
    }

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

    @Override
    public PageContent<Event> getByAuthorOrAssistant(String authorId, Integer page, Integer size) {
        int pageIndex = normalizePage(page);

        Page<EventModel> eventModels = repository.findByAuthorOrAssistant(
                authorId,
                Status.ACTIVE,
                PageRequest.of(pageIndex, size)
        );

        return new PageContent<>(
                eventModels.getContent().stream()
                        .map(EventEntityMapper::toDomain)
                        .collect(Collectors.toList()),
                eventModels.getNumber() + 1,
                eventModels.hasNext() ? eventModels.getNumber() + 2 : null
        );
    }
}
