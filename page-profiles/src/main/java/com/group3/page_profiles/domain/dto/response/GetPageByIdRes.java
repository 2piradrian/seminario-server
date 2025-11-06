package com.group3.page_profiles.domain.dto.response;

import com.group3.entity.PageType;
import com.group3.entity.User;
import com.group3.entity.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetPageByIdRes {

    private final String id;

    private final String name;

    private final String portraitImage;

    private final String profileImage;

    private final String shortDescription;

    private final String longDescription;

    private final User owner;

    private final List<User> members;

    private final PageType pageType;

    private final Integer followersCount;

    private final Boolean isFollowing;
    
}
