package com.group3.page_profiles.data.repository;

import com.group3.page_profiles.data.datasource.events_server.repository.EventsServerRepositoryI;
import com.group3.page_profiles.domain.repository.EventsRepositoryI;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class EventsRepository implements EventsRepositoryI {

    private final EventsServerRepositoryI eventsServerRepository;

    @Override
    public void deleteEventsByPageId(String pageId, String secret) {
        this.eventsServerRepository.deleteEventsFromPage(pageId, secret);
    }
}