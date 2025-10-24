package com.group3.events.presentation.service;

import com.group3.events.domain.dto.event.request.CreateEventReq;
import com.group3.events.domain.dto.event.request.EditEventReq;
import com.group3.events.domain.dto.event.request.GetEventByIdReq;
import com.group3.events.domain.dto.event.response.CreateEventRes;
import com.group3.events.domain.dto.event.response.EditEventRes;
import com.group3.events.domain.dto.event.response.GetEventByIdRes;

public interface EventServiceI {

    CreateEventRes create(CreateEventReq dto);

    GetEventByIdRes getById(GetEventByIdReq dto);

    EditEventRes edit(EditEventReq dto);

}
