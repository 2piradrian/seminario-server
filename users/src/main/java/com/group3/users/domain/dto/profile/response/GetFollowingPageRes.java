package com.group3.users.domain.dto.profile.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetFollowingPageRes {

    private final List<Object> following;

    private final Integer nextPage;

}
