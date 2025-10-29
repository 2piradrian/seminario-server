package com.group3.users.domain.dto.profile.response;

import com.group3.entity.Instrument;
import com.group3.entity.Style;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class GetUserProfileByIdRes {

    private final String id;

    private final String name;

    private final String surname;

    private final String email;

    private final LocalDateTime memberSince;

    private final String portraitImage;

    private final String profileImage;

    private final String shortDescription;

    private final String longDescription;

    private final List<Style> styles;

    private final List<Instrument> instruments;

    private final Integer followersCount;

    private final Integer followingCount;

    private final Boolean ownProfile;

    private final Boolean isFollowing;

}
