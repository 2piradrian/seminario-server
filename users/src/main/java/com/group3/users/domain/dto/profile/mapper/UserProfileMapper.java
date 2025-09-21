package com.group3.users.domain.dto.profile.mapper;

import com.group3.users.domain.dto.profile.mapper.implementation.*;

public class UserProfileMapper {

    public static EditMapper edit(){
        return new EditMapper();
    };

}
