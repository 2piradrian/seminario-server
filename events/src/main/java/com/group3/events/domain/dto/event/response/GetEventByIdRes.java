package com.group3.events.domain.dto.event.response;

import com.group3.entity.PageProfile;
import com.group3.entity.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@AllArgsConstructor
public class GetEventByIdRes {

    private final UserProfile author;

    private final String eventId;

    private final PageProfile pageProfile;

    private final String imageId;

    private final String title;

    private final String content;

    private final Integer views;

    private final Date dateInit;

    private final Date dateEnd;

    private final LocalDateTime createdAt;

}
