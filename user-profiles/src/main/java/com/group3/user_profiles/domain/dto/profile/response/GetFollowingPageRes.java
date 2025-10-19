package com.group3.user_profiles.domain.dto.profile.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
public class GetFollowingPageRes {

    private final List<Object> following;

    private final Integer nextPage;

}
