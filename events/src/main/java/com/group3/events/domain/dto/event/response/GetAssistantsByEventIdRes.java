package com.group3.events.domain.dto.event.response;

import com.group3.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetAssistantsByEventIdRes {

    private final List<User> assistants;
}
