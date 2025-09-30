package com.group3.pages.domain.dto.mapper;

import com.group3.pages.domain.dto.mapper.implementation.*;

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
    
}
