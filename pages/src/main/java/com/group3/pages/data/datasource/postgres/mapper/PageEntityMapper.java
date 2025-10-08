package com.group3.pages.data.datasource.postgres.mapper;

import com.group3.entity.Page;
import com.group3.entity.PageType;
import com.group3.entity.UserProfile;
import com.group3.pages.data.datasource.postgres.model.PageModel;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PageEntityMapper {
    
    public static Page toDomain(PageModel pageModel) {
        UserProfile owner = new UserProfile();
        owner.setId(pageModel.getOwnerId());

        return new Page(
            pageModel.getId(),
            pageModel.getName(),
            pageModel.getPortraitImage(),
            pageModel.getProfileImage(),
            pageModel.getShortDescription(),
            pageModel.getLongDescription(),
            owner,
            pageModel.getMembers()
                .stream()
                .map(id -> {
                    UserProfile member = new UserProfile();
                    member.setId(id);
                    return member;
                })
                .collect(java.util.stream.Collectors.toList()),
            pageModel.getStatus(),
            new PageType(pageModel.getPageTypeId(), null)
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
            page.getOwner().getId(),
            page.getMembers()
                .stream()
                .map(UserProfile::getId)
                .collect(Collectors.toList()),
            page.getStatus(),
            page.getPageType().getId()
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
