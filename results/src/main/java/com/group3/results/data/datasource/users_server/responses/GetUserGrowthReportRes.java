package com.group3.results.data.datasource.users_server.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetUserGrowthReportRes {

    private Long yearlyRegisteredUsers;

    private Long monthlyRegisteredUsers;

    private Long weeklyRegisteredUsers;

}
