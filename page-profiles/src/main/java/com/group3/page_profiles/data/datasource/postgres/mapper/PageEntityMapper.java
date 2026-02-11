package com.group3.page_profiles.data.datasource.postgres.mapper;

import com.group3.entity.PageProfile;
import com.group3.entity.PageType;
import com.group3.entity.User;
import com.group3.entity.UserProfile;
import com.group3.page_profiles.data.datasource.postgres.model.PageProfileModel;

import java.time.LocalDateTime;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PageEntityMapper {
    
    public static PageProfile toDomain(PageProfileModel page) {
        User owner = new User();
        owner.setId(page.getOwnerId());

        return PageProfile.builder()
            .id(page.getId())
            .name(page.getName())
            .portraitImage(page.getPortraitImage())
            .profileImage(page.getProfileImage())
            .shortDescription(page.getShortDescription())
            .longDescription(page.getLongDescription())
            .owner(owner)
            .members(page.getMembers()
                .stream()
                .map(id -> {
                    User member = new User();
                    member.setId(id);
                    return member;
                })
                .collect(Collectors.toList()))
            .status(page.getStatus())
            .pageType(new PageType(page.getPageTypeId(), null))
            .isFollowing(false)
            .build();
    }

    public static PageProfileModel toModel(PageProfile page) {
        PageProfileModel model = new PageProfileModel();
        model.setId(page.getId());
        model.setName(page.getName());
        model.setPortraitImage(page.getPortraitImage());
        model.setProfileImage(page.getProfileImage());
        model.setShortDescription(page.getShortDescription());
        model.setLongDescription(page.getLongDescription());
        model.setOwnerId(page.getOwner().getId());
        model.setMembers(page.getMembers()
            .stream()
            .map(User::getId)
            .collect(Collectors.toList()));
        model.setStatus(page.getStatus());
        model.setPageTypeId(page.getPageType().getId());
        return model;
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
