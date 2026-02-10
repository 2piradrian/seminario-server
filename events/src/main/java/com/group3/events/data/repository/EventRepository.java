package com.group3.events.data.repository;

import com.group3.entity.*;
import com.group3.events.data.datasource.postgres.mapper.EventEntityMapper;
import com.group3.events.data.datasource.postgres.model.EventModel;
import com.group3.events.data.datasource.postgres.repository.PostgresEventRepositoryI;
import com.group3.events.domain.repository.EventRepositoryI;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
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
    public void updateBatch(List<Event> events) {
        List<EventModel> models = events.stream()
            .map(EventEntityMapper::toModel)
            .collect(Collectors.toList());

        this.repository.saveAll(models);
    }

    @Override
    public PageContent<Event> getByAuthorOrAssistant(String authorId, Integer page, Integer size) {
        int pageIndex = normalizePage(page);

        Page<EventModel> eventModels = repository.findByAuthorOrAssistant(
                authorId,
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

    @Override
    public List<Event> getInDateRange(String userId, Date dateStart, Date dateEnd){
        return this.repository.findEventsInDateRange(
            dateStart,
            dateEnd,
            userId
        ).stream()
            .map(EventEntityMapper::toDomain)
            .collect(Collectors.toList());
    }

    // ======== Get Events by Filtered Page with Pagination ========

    @Override
    public PageContent<Event> getFilteredEvents(Integer page, Integer size, String text, Date dateInit, Date dateEnd) {
        int pageIndex = normalizePage(page);

        Page<EventModel> eventModels = repository.findByFilteredPage(
            text,
            dateInit,
            dateEnd,
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

    @Override
    public PageContent<Event> getOnlyPageEvents(Integer page, Integer size) {
        int pageIndex = (page != null && page > 0) ? page - 1 : 0;

        Page<EventModel> eventModels = this.repository.findOnlyPageEvents(
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


    @Override
    public PageContent<Event> getExpiredEvents(LocalDateTime now, Integer size) {

        Date dateNow = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());

        Slice<EventModel> eventModels = repository.findExpiredEvents(
            EventStatus.IN_PROGRESS,
            dateNow,
            PageRequest.of(0, size)
        );

        return new PageContent<>(
            eventModels.getContent().stream()
                .map(EventEntityMapper::toDomain)
                .collect(Collectors.toList()),
            eventModels.getNumber() + 1,
            eventModels.hasNext() ? eventModels.getNumber() + 2 : null
        );
    }

    @Override
    public PageContent<Event> getInactiveEventsToActivate(LocalDateTime now, Integer size) {

        Date dateNow = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());

        Slice<EventModel> eventModels = repository.findReadyToStartEvents(
            EventStatus.UPCOMING,
            dateNow,
            PageRequest.of(0, size)
        );

        return new PageContent<>(
            eventModels.getContent().stream()
                .map(EventEntityMapper::toDomain)
                .collect(Collectors.toList()),
            eventModels.getNumber() + 1,
            eventModels.hasNext() ? eventModels.getNumber() + 2 : null
        );
    }

    @Override
    public void deleteById(String eventId) {
        this.repository.deleteById(eventId);
    }

    @Override
    public void deleteByAuthorId(String authorId) {
        this.repository.deleteByAuthorId(authorId);
    }

    @Override
    public void deleteByPageId(String pageId) {
        this.repository.deleteByPageId(pageId);
    }

    @Override
    public void removeAssistantFromAllEvents(String userId) {
        this.repository.removeAssistantFromAllEvents(userId);
    }

    @Override
    public TimeReportContent getGrowthReport(LocalDateTime lastYear, LocalDateTime lastMonth, LocalDateTime lastWeek) {
        return this.repository.getGrowthReport(
            lastYear,
            lastMonth,
            lastWeek
        );
    }

}
