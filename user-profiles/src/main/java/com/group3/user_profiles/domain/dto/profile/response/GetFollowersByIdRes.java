package com.group3.user_profiles.domain.dto.profile.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetFollowersByIdRes {

    private final Integer followersCount;

}
