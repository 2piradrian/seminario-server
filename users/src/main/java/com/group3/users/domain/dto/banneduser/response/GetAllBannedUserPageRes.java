package com.group3.users.domain.dto.banneduser.response;

import com.group3.entity.BannedUser;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetAllBannedUserPageRes {
    private final List<BannedUser> bannedUsers;
    private final Integer nextPage;
}
