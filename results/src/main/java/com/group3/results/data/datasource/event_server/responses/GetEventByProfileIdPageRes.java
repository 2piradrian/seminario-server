package com.group3.results.data.datasource.event_server.responses;

import com.group3.entity.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetEventByProfileIdPageRes {

    private final List<Event> events;

    private final Integer nextPage;

}
