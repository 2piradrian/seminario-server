package com.group3.users.domain.dto.follow.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetFollowerPageRes {

    private final List<Object> followers;

    private final Integer nextPage;

}
