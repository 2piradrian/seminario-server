package com.group3.profiles.domain.dto.profile.mapper;

import com.group3.profiles.domain.dto.profile.mapper.implementation.*;

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

    public static GetPageByFullnameMapper getByFullname(){
        return new GetPageByFullnameMapper();
    }

    public static GetOwnMapper getOwnProfile() {
        return new GetOwnMapper();
    }

}
