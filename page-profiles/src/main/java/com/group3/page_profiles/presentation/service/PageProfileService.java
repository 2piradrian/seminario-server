package com.group3.page_profiles.presentation.service;

import com.group3.config.PrefixedUUID;
import com.group3.entity.*;
import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import com.group3.page_profiles.config.helpers.SecretKeyHelper;
import com.group3.page_profiles.data.repository.*;
import com.group3.page_profiles.domain.dto.mapper.PageMapper;
import com.group3.page_profiles.domain.dto.request.*;
import com.group3.page_profiles.domain.dto.response.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class PageProfileService implements PageProfileServiceI {

    private final SecretKeyHelper secretKeyHelper;

    private final CatalogRepository catalogRepository;

    private final PageProfileRepository pageProfileRepository;

    private final UserRepository userRepository;

    private final ImagesRepository imagesRepository;


    // ======== Create Page ========

    @Override
    public CreatePageRes create(CreatePageReq dto) {

        User user = this.userRepository.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        PageProfile existsPage = this.pageProfileRepository.getByName(dto.getName());
        if (existsPage != null) throw new ErrorHandler(ErrorType.PAGENAME_ALREADY_EXISTS);

        PageProfile page = new PageProfile();
        page.setId(PrefixedUUID.generate(PrefixedUUID.EntityType.PAGE).toString());

        PageType pageType = this.catalogRepository.getById(dto.getPageType().getId());
        if(pageType == null) throw new ErrorHandler(ErrorType.PAGE_TYPE_NOT_FOUND);
        page.setPageType(pageType);

        page.setOwner(user.getProfile());
        page.setMembers(List.of(user.getProfile()));

        page.setName(dto.getName());
        page.setPortraitImage("");
        page.setProfileImage("");
        page.setShortDescription("¡New page!");
        page.setLongDescription("¡New page!");
        page.setStatus(Status.ACTIVE);

        PageProfile newPage = this.pageProfileRepository.save(page);
        return PageMapper.create().toResponse(newPage);
    }


    // ======== Get Page by ID ========

    @Override
    public GetPageByIdRes getById(GetPageByIdReq dto) {
        // TODO
        User user = this.userRepository.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.USER_NOT_FOUND);

        PageProfile page = this.pageProfileRepository.getById(dto.getPageId());
        if (page == null) throw new ErrorHandler(ErrorType.PAGE_NOT_FOUND);

        User sessionProfile = this.userRepository.getByIdWithFollowers(user.getId(), this.secretKeyHelper.getSecret());
        if (sessionProfile == null) throw new ErrorHandler(ErrorType.USER_NOT_FOUND);

        for (UserProfile member : page.getMembers()){
            if (member == null || member.getId() == null) throw new ErrorHandler(ErrorType.USER_NOT_FOUND);

            User completeMember = this.userRepository.getById(member.getId());
            if (completeMember == null) throw new ErrorHandler(ErrorType.USER_NOT_FOUND);

            member.setPortraitImage(completeMember.getProfile().getPortraitImage());
            member.setProfileImage(completeMember.getProfile().getProfileImage());
            member.setName(completeMember.getProfile().getName());
            member.setSurname(completeMember.getProfile().getSurname());
            member.setStyles(completeMember.getProfile().getStyles());
            member.setInstruments(completeMember.getProfile().getInstruments());
        }

        Boolean isFollowing = sessionProfile.getProfile().getFollowing().contains(page.getId());
        Integer followers = this.userRepository.getFollowersById(dto.getPageId(), secretKeyHelper.getSecret());

        return PageMapper.getPage().toResponse(page, followers, isFollowing);
    }


    // ======== Get Page Profile Page Filtered ========

    @Override
    public GetPageProfilePageFilteredRes getProfileFiltered(GetPageProfilePageFilteredReq dto) {
        if (!this.secretKeyHelper.isValid(dto.getSecret())) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        PageContent<PageProfile> pages = this.pageProfileRepository.getFilteredPage(
            dto.getName(),
            dto.getPageTypeId(),
            dto.getPage(),
            dto.getSize()
        );

        return PageMapper.getFiltered().toResponse(pages);
    }

    // ======== Get Pages by User ========

    @Override
    public GetPageByUserIdRes getUserPages(GetPageByUserIdReq dto) {
        User user = this.userRepository.getById(dto.getUserId());
        if(user == null) throw new ErrorHandler(ErrorType.USER_NOT_FOUND);

        List<PageProfile> pages = this.pageProfileRepository.getByUserId(dto.getUserId());
        return PageMapper.getUserPages().toResponse(pages);
    }


    // ======== Get Pages by List of IDs ========

    @Override
    public GetPageListByIdsRes getListByIds(GetPageListByIdsReq dto) {
        if (!this.secretKeyHelper.isValid(dto.getSecret())) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        List<PageProfile> pages = this.pageProfileRepository.getListByIds(dto.getPageIds());
        return PageMapper.getListByIds().toResponse(pages);
    }


    // ======== Edit Page ========

    @Override
    public void edit(EditPageReq dto) {
        User user = this.userRepository.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        PageProfile page = this.pageProfileRepository.getById(dto.getPageId());
        if (page == null) throw new ErrorHandler(ErrorType.PAGE_NOT_FOUND);
        if (!page.getOwner().getId().equals(user.getId())) throw new ErrorHandler(ErrorType.UNAUTHORIZED);


        // ======== Update Profile Image ========
        if (dto.getProfileImage() != null) {
            String profileImage = page.getProfileImage();
            if (profileImage != null) {
                this.imagesRepository.delete(profileImage, secretKeyHelper.getSecret());
            }
            String profileId = this.imagesRepository.upload(dto.getProfileImage(), secretKeyHelper.getSecret());
            page.setProfileImage(profileId);
        }

        // ======== Update Portrait Image ========
        if (dto.getPortraitImage() != null) {
            String portraitImage = page.getPortraitImage();
            if (portraitImage != null) {
                this.imagesRepository.delete(portraitImage, secretKeyHelper.getSecret());
            }
            String portraitId = this.imagesRepository.upload(dto.getPortraitImage(), secretKeyHelper.getSecret());
            page.setPortraitImage(portraitId);
        }

        // ======== Validate and Update Members ========
        dto.getMembers().stream()
                .map(memberId -> userRepository.getById(memberId))
                .forEach(userProfile -> {
                    if (userProfile == null) throw new ErrorHandler(ErrorType.USER_NOT_FOUND);
                });

        List<UserProfile> members = page.getMembers();
        Set<UserProfile> existingMembers = new HashSet<>(members);

        dto.getMembers().forEach(id -> {
            User userProfile = userRepository.getById(id);
            if (existingMembers.add(userProfile.getProfile())) {
                members.add(userProfile.getProfile());
            }
        });
        page.setMembers(members);

        // ======== Update Page Type and Metadata ========
        PageType pageType = this.catalogRepository.getById(dto.getPageType().getId());
        if(pageType == null) throw new ErrorHandler(ErrorType.PAGE_TYPE_NOT_FOUND);
        page.setPageType(pageType);

        page.setName(dto.getName());
        page.setShortDescription(dto.getShortDescription());
        page.setLongDescription(dto.getLongDescription());

        this.pageProfileRepository.update(page);
    }


    // ======== Delete Page ========

    @Override
    public void delete(DeletePageReq dto) {
        User user = this.userRepository.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.USER_NOT_FOUND);

        PageProfile page = this.pageProfileRepository.getById(dto.getPageId());

        if (!user.canDelete(page)) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        page.setStatus(Status.DELETED);
        this.pageProfileRepository.update(page);
    }

}
