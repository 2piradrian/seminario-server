package com.group3.users.domain.dto.user.mapper;

import com.group3.users.domain.dto.user.mapper.implementation.*;

public class UserMapper {

    public static GetByIdMapper getById() {
        return new GetByIdMapper();
    }

    public static DeleteMapper delete() {
        return new DeleteMapper();
    }

    public static GetAllStaffMapper getAllStaff() {
        return new GetAllStaffMapper();
    }

}
