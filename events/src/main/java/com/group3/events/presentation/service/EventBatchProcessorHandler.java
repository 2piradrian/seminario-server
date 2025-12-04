package com.group3.events.presentation.service;

import com.group3.entity.Event;
import com.group3.entity.EventStatus;
import com.group3.entity.PageContent;
import com.group3.events.data.repository.EventRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class EventBatchProcessorHandler {

    private final EventRepository eventRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean processExpirationBatch(LocalDateTime checkDate, int batchSize) {

        PageContent<Event> events = this.eventRepository.getExpiredEvents(checkDate, batchSize);

        if (events.getContent().isEmpty())
            return false;

        LocalDateTime now = LocalDateTime.now();

        events.getContent().forEach(event -> {
            event.setStatus(EventStatus.ENDED);
            event.setUpdatedAt(now);
        });

        this.eventRepository.updateBatch(events.getContent());

        return events.getContent().size() == batchSize;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean processActivationBatch(LocalDateTime checkDate, int batchSize) {

        PageContent<Event> events = this.eventRepository.getInactiveEventsToActivate(checkDate, batchSize);

        if (events.getContent().isEmpty())
            return false;

        LocalDateTime now = LocalDateTime.now();

        events.getContent().forEach(event -> {
            event.setStatus(EventStatus.IN_PROGRESS);
            event.setUpdatedAt(now);
        });

        this.eventRepository.updateBatch(events.getContent());

        return events.getContent().size() == batchSize;
    }

}
