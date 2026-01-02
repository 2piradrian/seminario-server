package com.group3.users.domain.dto.user.mapper;

import com.group3.users.domain.dto.user.mapper.implementation.*;

public class UserMapper {

    public static GetByIdMapper getById() {
        return new GetByIdMapper();
    }

    public static GetAllStaffMapper getAllStaff() {
        return new GetAllStaffMapper();
    }

    public static DeleteMapper delete() {
        return new DeleteMapper();
    }

    public static GetPageFilteredMapper getFiltered(){
        return new GetPageFilteredMapper();
    }

    public static GetMutualsFollowersMapper getMutualsFollowers(){
        return new GetMutualsFollowersMapper();
    }

    public static EditMapper update() {
        return new EditMapper();
    }

    public static GetByListOfIdsPageMapper getByListOfIdsPage() { return new GetByListOfIdsPageMapper(); }

}
