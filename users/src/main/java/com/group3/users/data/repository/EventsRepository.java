package com.group3.users.data.repository;

import com.group3.users.data.datasource.events_server.repository.EventsServerRepositoryI;
import com.group3.users.domain.repository.EventsRepositoryI;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class EventsRepository implements EventsRepositoryI {

    private final EventsServerRepositoryI repository;

    @Override
    public void delete(String token, String eventId) {
        this.repository.delete(token, eventId);
    }

}
