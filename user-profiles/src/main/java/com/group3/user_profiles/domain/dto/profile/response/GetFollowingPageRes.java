package com.group3.user_profiles.domain.dto.profile.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@AllArgsConstructor
@Setter
public class GetFollowingPageRes {

    List<Object> following;

    Integer nextPage;

}
