package com.group3.users.domain.dto.user.response;

import com.group3.entity.User;
import com.group3.entity.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetUserPageFilteredRes {

    private final List<User> profiles;

    private final Integer nextPage;

}
