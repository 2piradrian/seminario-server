package com.group3.events.domain.dto.event.response;

import com.group3.entity.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetEventAndAsistsPageRes {

    private final List<Event> events;

    private final Integer nextPage;

}
