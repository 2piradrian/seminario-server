package com.group3.users.domain.dto.profile.response;

import com.group3.entity.Instrument;
import com.group3.entity.Style;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
public class CreateUserProfileRes {

    private final String userProfileId;

    private final String portaitImage;

    private final String profileImage;

    private final String shortDescription;

    private final String longDescription;

    private final Set<Style> styles;

    private final Set<Instrument> instruments;
  
}
