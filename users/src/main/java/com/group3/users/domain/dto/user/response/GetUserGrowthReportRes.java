package com.group3.users.domain.dto.user.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetUserGrowthReportRes {

    private final Long yearlyRegisteredUsers;

    private final Long weeklyRegisteredUsers;

    private final Long monthlyRegisteredUsers;

}