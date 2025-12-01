package com.group3.chat.data.datasource.users_server.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetFollowersByIdRes {

    private final Integer followersCount;

}
