package com.group3.page_profiles.data.datasource.postgres.mapper;

import com.group3.entity.PageProfile;
import com.group3.entity.PageType;
import com.group3.entity.User;
import com.group3.entity.UserProfile;
import com.group3.page_profiles.data.datasource.postgres.model.PageProfileModel;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PageEntityMapper {
    
    public static PageProfile toDomain(PageProfileModel page) {
        User owner = new User();
        owner.setId(page.getOwnerId());

        return new PageProfile(
            page.getId(),
            page.getName(),
            page.getPortraitImage(),
            page.getProfileImage(),
            page.getShortDescription(),
            page.getLongDescription(),
            owner,
            page.getMembers()
                .stream()
                .map(id -> {
                    User member = new User();
                    member.setId(id);
                    return member;
                })
                .collect(Collectors.toList()),
            page.getStatus(),
            new PageType(page.getPageTypeId(), null),
            false
        );
    }

    public static PageProfileModel toModel(PageProfile page) {
        return new PageProfileModel(
            page.getId(),
            page.getName(),
            page.getPortraitImage(),
            page.getProfileImage(),
            page.getShortDescription(),
            page.getLongDescription(),
            page.getOwner().getId(),
            page.getMembers()
                .stream()
                .map(User::getId)
                .collect(Collectors.toList()),
            page.getStatus(),
            page.getPageType().getId()
        );
    }

        public static List<PageProfile> toDomain(List<PageProfileModel> page) {
        if (page == null) return Collections.emptyList();

        return page.stream()
            .map(PageEntityMapper::toDomain)
            .collect(Collectors.toList());
    }


    public static List<PageProfileModel> toModel(List<PageProfile> page) {
        if (page == null) return Collections.emptyList();

        return page.stream()
            .map(PageEntityMapper::toModel)
            .collect(Collectors.toList());
    }
}
