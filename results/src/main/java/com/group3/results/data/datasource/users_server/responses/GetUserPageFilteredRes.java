package com.group3.results.data.datasource.users_server.responses;

import com.group3.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetUserPageFilteredRes {

    private final List<User> users;

    private final Integer nextPage;

}
