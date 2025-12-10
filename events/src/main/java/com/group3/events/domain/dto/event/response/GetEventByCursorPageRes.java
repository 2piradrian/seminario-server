package com.group3.events.domain.dto.event.response;

import com.group3.entity.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class GetEventByCursorPageRes {

    private final List<Event> events;

    private final LocalDateTime nextCursor;

}
