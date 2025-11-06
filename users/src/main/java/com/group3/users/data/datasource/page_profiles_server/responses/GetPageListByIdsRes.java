package com.group3.users.data.datasource.page_profiles_server.responses;

import com.group3.entity.PageProfile;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetPageListByIdsRes {

    private final List<PageProfile> pages;
    
}
