package com.group3.users.domain.dto.profile.mapper;


import com.group3.users.domain.dto.profile.mapper.implementation.*;

public class UserProfileMapper {

    public static GetByIdMapper getById() {
        return new GetByIdMapper();
    }

    public static CreateMapper create(){
        return new CreateMapper();
    }

    public static DeleteMapper delete() {
        return new DeleteMapper();
    }

    public static EditMapper update() {
        return new EditMapper();
    }

    public static GetPageFilteredMapper getFiltered(){
        return new GetPageFilteredMapper();
    }

    public static ActiveMapper active() {
        return new ActiveMapper();
    }

    public static GetOwnMapper getOwnProfile() {
        return new GetOwnMapper();
    }

}
