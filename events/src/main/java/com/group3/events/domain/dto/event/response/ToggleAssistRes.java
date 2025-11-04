package com.group3.events.domain.dto.event.response;

import com.group3.entity.PageProfile;
import com.group3.entity.User;
import com.group3.entity.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ToggleAssistRes {

    private final User author;

    private final String eventId;

    private final PageProfile pageProfile;

    private final String imageId;

    private final String title;

    private final String content;

    private final Integer views;

    private final LocalDateTime createdAt;

}
