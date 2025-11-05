package com.group3.results.data.repository;

import com.group3.entity.Event;
import com.group3.results.data.datasource.event_server.repository.EventServerRepositoryI;
import com.group3.results.data.datasource.event_server.responses.GetFilteredEventPageRes;
import com.group3.results.domain.repository.EventRepositoryI;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class EventRepository implements EventRepositoryI {

    private final EventServerRepositoryI repository;

    @Override
    public List<Event> getFilteredEvents(Integer page, Integer size, String text, String secret, Date dateInit, Date dateEnd) {
        Map<String,Object> payload = new HashMap<>();

        payload.put("page", page);
        payload.put("size", size);
        payload.put("text", text);
        payload.put("dateInit", dateInit);
        payload.put("dateEnd", dateEnd);
        payload.put("secret", secret);

        GetFilteredEventPageRes response = this.repository.getFilteredEvents(payload);

        return response.getEvents();
    }

}
