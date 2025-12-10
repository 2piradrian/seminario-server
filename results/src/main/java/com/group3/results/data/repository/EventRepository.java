package com.group3.results.data.repository;

import com.group3.entity.Event;
import com.group3.results.data.datasource.event_server.repository.EventServerRepositoryI;
import com.group3.results.data.datasource.event_server.responses.GetEventByProfileIdPageRes;
import com.group3.results.data.datasource.event_server.responses.GetFilteredEventPageRes;
import com.group3.results.domain.repository.EventRepositoryI;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class EventRepository implements EventRepositoryI {

    private final EventServerRepositoryI repository;

    @Override
    public List<Event> getFilteredEventsPage(String token, Integer page, Integer size, String text, String secret, String dateInit, String dateEnd) {
        GetFilteredEventPageRes response = this.repository.getFilteredEvents(
             token, secret, page, size, text, dateInit, dateEnd
        );

        return response.getEvents();
    }

    @Override
    public List<Event> getEventsByProfileIdPage(String token, String profileId, Integer page, Integer size) {
        GetEventByProfileIdPageRes response = this.repository.getEventsByProfileId(
            token, profileId, page, size
        );

        return response.getEvents();
    }

}
