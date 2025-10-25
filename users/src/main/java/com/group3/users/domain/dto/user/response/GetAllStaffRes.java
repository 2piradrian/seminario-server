package com.group3.users.domain.dto.user.response;

import com.group3.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetAllStaffRes {

    private final List<User> users;

}
