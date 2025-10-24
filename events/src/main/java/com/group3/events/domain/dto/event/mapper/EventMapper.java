package com.group3.events.domain.dto.event.mapper;

import com.group3.events.domain.dto.event.mapper.implementation.CreateMapper;

public class EventMapper {

    public static CreateMapper create() {
        return new CreateMapper();
    }

}
