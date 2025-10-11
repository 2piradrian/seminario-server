package com.group3.user_profiles.domain.dto.profile.mapper;

import com.group3.user_profiles.domain.dto.profile.mapper.implementation.*;

public class UserProfileMapper {

    public static GetPageMapper getPage() {return new GetPageMapper();}

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

    public static GetOwnMapper getOwnProfile() {
        return new GetOwnMapper(); }

}
