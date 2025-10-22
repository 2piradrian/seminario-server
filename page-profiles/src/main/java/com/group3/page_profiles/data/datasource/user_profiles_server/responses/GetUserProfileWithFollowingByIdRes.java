package com.group3.page_profiles.data.datasource.user_profiles_server.responses;

import com.group3.entity.Instrument;
import com.group3.entity.Style;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class GetUserProfileWithFollowingByIdRes {

    private final String id;

    private final String name;

    private final String surname;

    private final String email;

    private final LocalDateTime memberSince;

    private final List<Style> styles;

    private final List<Instrument> instruments;

    private final List<String> following;

}
