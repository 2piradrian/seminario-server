package com.group3.events.data.datasource.postgres.mapper;

import com.group3.entity.Event;
import com.group3.entity.PageProfile;
import com.group3.entity.User;
import com.group3.entity.UserProfile;
import com.group3.events.data.datasource.postgres.model.EventModel;

public class EventEntityMapper {

    public static Event toDomain(EventModel eventModel) {
        return new Event(
                eventModel.getId(),
                User.builder().id(eventModel.getAuthorId()).build(),
                PageProfile.builder().id(eventModel.getPageId()).build(),
                eventModel.getTitle(),
                eventModel.getContent(),
                eventModel.getImageId(),
                eventModel.getDateInit(),
                eventModel.getDateEnd(),
                eventModel.getViews(),
                eventModel.getCreatedAt(),
                eventModel.getUpdatedAt(),
                eventModel.getAssists(),
                0,
                false,
                eventModel.getStatus()
        );
    }

    public static EventModel toModel(Event event) {
        return new EventModel(
                event.getId(),
                event.getAuthor().getId(),
                event.getPageProfile().getId(),
                event.getTitle(),
                event.getImageId(),
                event.getContent(),
                event.getDateInit(),
                event.getDateEnd(),
                event.getCreatedAt(),
                event.getUpdatedAt(),
                event.getViews(),
                event.getAssists(),
                event.getStatus()
        );
    }
}
