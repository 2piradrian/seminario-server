package com.group3.pages.data.postgres.mapper;

import com.group3.entity.Page;
import com.group3.pages.data.postgres.model.PageModel;

public class PageEntityMapper {
    
    public static Page toDomain(PageModel pageModel) {
        return new Page(
            pageModel.getId(),
            pageModel.getName(),
            pageModel.getImageId(),
            pageModel.getOwnerId(),
            pageModel.getMembers()
        );
    }

    public static PageModel toModel(Page page) {
        return new PageModel(
            page.getId(),
            page.getName(),
            page.getImageId(),
            page.getOwnerId(),
            page.getMembers()
        );
    }
    
}
