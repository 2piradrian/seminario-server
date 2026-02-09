package com.group3.page_profiles.domain.dto.mapper;

import com.group3.page_profiles.domain.dto.mapper.implementation.*;

public class PageMapper {
    
    public static GetByIdMapper getPage() {
        return new GetByIdMapper();
    }

    public static CreateMapper create() {
        return new CreateMapper();
    }

    public static GetByUserIdMapper getUserPages() {
        return new GetByUserIdMapper();
    }

    public static DeleteMapper delete() {
        return new DeleteMapper();
    }

    public static EditMapper edit() {
        return new EditMapper();
    }

    public static JoinPageMapper joinPage() {
        return new JoinPageMapper();
    }

    public static LeavePageMapper leavePage() {
        return new LeavePageMapper();
    }

    public static GetPageFilteredMapper getFiltered(){
        return new GetPageFilteredMapper();
    }

    public static GetListByIdsMapper getListByIds() {
        return new GetListByIdsMapper();
    }

    public static DeleteUserPagesMapper deleteUserPages() {
        return new DeleteUserPagesMapper();
    }
    
}
