package com.group3.pages.presentation.service;

import com.group3.entity.*;
import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import com.group3.pages.config.helpers.SecretKeyHelper;
import com.group3.pages.data.repository.*;
import com.group3.pages.domain.dto.mapper.PageMapper;
import com.group3.pages.domain.dto.request.*;
import com.group3.pages.domain.dto.response.GetPageByIdRes;
import com.group3.pages.domain.dto.response.GetPageByUserIdRes;
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
public class PageService implements PageServiceI{

    private final SecretKeyHelper secretKeyHelper;

    private final CatalogRepository catalogRepository;

    private final PageRepository pageRepository;

    private final UserRepository userRepository;

    private final ProfileRepository profileRepository;

    private final ImagesRepository imagesRepository;

    @Override
    public void create(CreatePageReq dto) {

        User user = this.userRepository.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        Page page = new Page();

        PageType pageType = this.catalogRepository.getById(dto.getIdPageType());
        if(pageType == null) throw new ErrorHandler(ErrorType.PAGE_TYPE_NOT_FOUND);

        page.setPageType(pageType);

        UserProfile owner = this.profileRepository.getById(user.getId());
        if(owner == null) throw new ErrorHandler(ErrorType.USER_NOT_FOUND);

        page.setOwner(owner);
        page.setMembers(List.of(owner));

        page.setName(dto.getName());
        page.setPortraitImage("");
        page.setProfileImage("");
        page.setShortDescription("¡New page!");
        page.setLongDescription("¡New page!");

        page.setStatus(Status.ACTIVE);

        this.pageRepository.save(page);
    }

    @Override
    public GetPageByIdRes getById(GetPageByIdReq dto) {
        Page page = this.pageRepository.getById(dto.getPageId());
        if (page == null) throw new ErrorHandler(ErrorType.PAGE_NOT_FOUND);

        if(page.getOwner() == null) throw new ErrorHandler(ErrorType.USER_NOT_FOUND);

        return PageMapper.getPage().toResponse(page);
    }

    @Override
    public GetPageByUserIdRes getUserPages(GetPageByUserIdReq dto) {

        User user = this.userRepository.getById(dto.getUserId());
        if(user == null) throw new ErrorHandler(ErrorType.USER_NOT_FOUND);

        List<Page> pages = this.pageRepository.findByUserId(dto.getUserId());

        return PageMapper.getUserPages().toResponse(pages);
    }

    @Override
    public void delete(DeletePageReq dto) {

        User user = this.userRepository.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.USER_NOT_FOUND);

        Page page = this.pageRepository.getById(dto.getPageId());

        boolean isOwner = page.getOwner().getId().equals(user.getId());
        boolean isAdmin = user.getRoles().contains(Role.ADMIN);
        boolean isModerator = user.getRoles().contains(Role.MODERATOR);

        if (!isOwner && !isAdmin && !isModerator) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        page.setStatus(Status.DELETED);

        this.pageRepository.update(page);
    }

    @Override
    public void edit(EditPageReq dto) {
        User user = this.userRepository.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        Page page = this.pageRepository.getById(dto.getPageId());
        if (page == null) throw new ErrorHandler(ErrorType.PAGE_NOT_FOUND);

        if (!page.getOwner().getId().equals(user.getId())) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        if (dto.getProfileImage() != null){
            String profileImage = page.getProfileImage();
            if (profileImage != null) {
                this.imagesRepository.delete(profileImage, secretKeyHelper.getSecret());
            }
            String profileId = this.imagesRepository.upload(dto.getProfileImage(), secretKeyHelper.getSecret());
            page.setProfileImage(profileId);
        }

        if (dto.getPortraitImage() != null){
            String portraitImage = page.getPortraitImage();
            if (portraitImage != null) {
                this.imagesRepository.delete(portraitImage, secretKeyHelper.getSecret());
            }

            String portraitId = this.imagesRepository.upload(dto.getPortraitImage(), secretKeyHelper.getSecret());
            page.setPortraitImage(portraitId);
        }

        dto.getMembers().stream()
            .map(profileRepository::getById)
            .forEach(userProfile -> {
                if (userProfile == null) {
                    throw new ErrorHandler(ErrorType.USER_NOT_FOUND);
                }
            });

        List<UserProfile> members = page.getMembers();
        Set<UserProfile> existingMembers = new HashSet<>(members);

        dto.getMembers()
            .forEach(id -> {
                UserProfile userProfile = profileRepository.getById(id);
                if (existingMembers.add(userProfile)){
                    members.add(userProfile);
                }
            });

        page.setMembers(members);

        PageType pageType = this.catalogRepository.getById(dto.getPageType().getId());
        if(pageType == null)throw new ErrorHandler(ErrorType.PAGE_TYPE_NOT_FOUND);

        page.setPageType(pageType);
        page.setName(dto.getName());
        page.setShortDescription(dto.getShortDescription());
        page.setLongDescription(dto.getLongDescription());

        this.pageRepository.update(page);
    }
    
}
