package com.group3.results.domain.dto.response;

import com.group3.entity.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetSearchResultFilteredRes {

    private final List<User> users;

    private final List<PageProfile> pageProfiles;

    private final List<Post> posts;

    private final List<Event> events;

}
