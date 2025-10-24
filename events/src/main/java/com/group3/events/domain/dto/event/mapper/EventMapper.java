package com.group3.events.domain.dto.event.mapper;

import com.group3.events.domain.dto.event.mapper.implementation.CreateMapper;
import com.group3.events.domain.dto.event.mapper.implementation.EditMapper;
import com.group3.events.domain.dto.event.mapper.implementation.GetByIdMapper;

public class EventMapper {

    public static CreateMapper create() {
        return new CreateMapper();
    }

    public static GetByIdMapper getById() {
        return new GetByIdMapper();
    }

    public static EditMapper edit() {
        return new EditMapper();
    }

}
