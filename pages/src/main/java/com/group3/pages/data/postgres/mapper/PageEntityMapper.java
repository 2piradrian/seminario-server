package com.group3.pages.data.postgres.mapper;

import com.group3.entity.Page;
import com.group3.pages.data.postgres.model.PageModel;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PageEntityMapper {
    
    public static Page toDomain(PageModel pageModel) {
        return new Page(
            pageModel.getId(),
            pageModel.getName(),
            pageModel.getPortraitImage(),
            pageModel.getProfileImage(),
            pageModel.getShortDescription(),
            pageModel.getLongDescription(),
            pageModel.getOwnerId(),
            pageModel.getMembers(),
            pageModel.getStatus(),
            pageModel.getPageType()
        );
    }

    public static PageModel toModel(Page page) {
        return new PageModel(
            page.getId(),
            page.getName(),
            page.getPortraitImage(),
            page.getProfileImage(),
            page.getShortDescription(),
            page.getLongDescription(),
            page.getOwnerId(),
            page.getMembers(),
            page.getStatus(),
            page.getPageType()
        );
    }

        public static List<Page> toDomain(List<PageModel> pageModels) {
        if (pageModels == null) return Collections.emptyList();

        return pageModels.stream()
            .map(PageEntityMapper::toDomain)
            .collect(Collectors.toList());
    }


    public static List<PageModel> toModel(List<Page> pages) {
        if (pages == null) return Collections.emptyList();

        return pages.stream()
            .map(PageEntityMapper::toModel)
            .collect(Collectors.toList());
    }
}
