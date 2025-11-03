package com.group3.users.domain.dto.follow.response;

import com.group3.entity.Follow;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetAllFollowingRes {

    private final List<Follow> following;

}
