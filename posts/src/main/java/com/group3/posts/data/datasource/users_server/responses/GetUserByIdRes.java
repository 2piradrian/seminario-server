package com.group3.posts.data.datasource.users_server.responses;

import com.group3.entity.Role;
import com.group3.entity.Status;
import com.group3.entity.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetUserByIdRes {

    private final String id;

    private final String email;

    private final Status status;

    private final Role role;

    private final UserProfile profile;

}
