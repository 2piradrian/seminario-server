package com.group3.users.domain.dto.follow.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetFollowingPageRes {

    private final List<Object> following;

    private final Integer nextPage;

}
