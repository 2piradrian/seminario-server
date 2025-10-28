package com.group3.events.domain.dto.event.mapper;

import com.group3.events.domain.dto.event.mapper.implementation.*;

public class EventMapper {

    public static CreateMapper create() {
        return new CreateMapper();
    }

    public static GetByIdMapper getById() {
        return new GetByIdMapper();
    }

    public static GetOwnEventMapper getOwnPage(){
        return new GetOwnEventMapper();
    }

    public static GetOwnAssistsMapper getOwnAsist() {
        return new GetOwnAssistsMapper();
    }

    public static EditMapper edit() {
        return new EditMapper();
    }

    public static ToggleAsistMapper toggleAsist(){
        return new ToggleAsistMapper();
    }

}
