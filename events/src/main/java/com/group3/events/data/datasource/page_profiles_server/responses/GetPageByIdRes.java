package com.group3.events.data.datasource.page_profiles_server.responses;

import com.group3.entity.PageType;
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

    private final UserProfile owner;

    private final List<UserProfile> members;

    private PageType pageType;
    
}
