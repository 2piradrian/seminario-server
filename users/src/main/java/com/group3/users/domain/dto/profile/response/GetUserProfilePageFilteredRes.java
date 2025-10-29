package com.group3.users.domain.dto.profile.response;

import com.group3.entity.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetUserProfilePageFilteredRes {

    private final List<UserProfile> profiles;

    private final Integer nextPage;

}
