package com.group3.users.domain.dto.user.response;

import com.group3.entity.Role;
import com.group3.entity.User;
import com.group3.entity.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
public class GetAllStaffRes {

    private final List<User> staff;

}
