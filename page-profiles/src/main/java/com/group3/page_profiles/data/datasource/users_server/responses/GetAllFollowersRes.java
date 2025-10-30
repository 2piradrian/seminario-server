package com.group3.page_profiles.data.datasource.users_server.responses;

import com.group3.entity.Follow;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetAllFollowersRes {

    private final List<Follow> followers;

}
