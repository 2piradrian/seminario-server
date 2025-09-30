package com.group3.pages.presentation.service;

import com.group3.entity.*;
import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import com.group3.pages.config.helpers.SecretKeyHelper;
import com.group3.pages.data.repository.ImagesRepository;
import com.group3.pages.data.repository.PageRepository;
import com.group3.pages.data.repository.UserRepository;
import com.group3.pages.domain.dto.mapper.PageMapper;
import com.group3.pages.domain.dto.request.*;
import com.group3.pages.domain.dto.response.GetPageByIdRes;
import com.group3.pages.domain.dto.response.GetPageByUserIdRes;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class PageService implements PageServiceI{

    private final SecretKeyHelper secretKeyHelper;

    private final PageRepository pageRepository;

    private final UserRepository userRepository;

    private final ImagesRepository imagesRepository;

    @Override
    public void create(CreatePageReq dto) {

        User user = this.userRepository.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        Page page = new Page();

        page.setName(dto.getName());
        page.setOwnerId(user.getId());
        page.setMembers(List.of(user.getId()));

        page.setPortraitImage("");
        page.setProfileImage("");
        page.setShortDescription("¡New page!");
        page.setLongDescription("¡New page!");

        page.setStatus(Status.ACTIVE);

        /*
        PageType pageType = this.catalogRepository.getPageTypeById(dto.getIdPageType());

        if(pageType == null){
            throw new ErrorHandler(ErrorType.PAGE_TYPE_NOTFOUND);
        }

        page.setPageType(pageType);
        */

        this.pageRepository.save(page);
    }

    @Override
    public GetPageByIdRes getById(GetPageByIdReq dto) {
        Page page = this.pageRepository.getById(dto.getPageId());
        if (page == null) throw new ErrorHandler(ErrorType.PAGE_NOT_FOUND);

        /*User owner = this.userRepository.getById(page.getOwnerId());
        if(owner == null) throw new ErrorHandler(ErrorType.USER_NOT_FOUND);*/

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

        boolean isOwner = page.getOwnerId().equals(user.getId());
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

        if (!page.getOwnerId().equals(user.getId())) {
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

        dto.getMembers().forEach(id -> {
            User userVerify = userRepository.getById(id);

            if (userVerify == null) {
                throw new ErrorHandler(ErrorType.USER_NOT_FOUND);
            }
        });

        /*

        PageType pageType = this.catalogRepository.getPageTypeById(dto.getIdPageType());

        if(pageType == null){
            throw new ErrorHandler(ErrorType.PAGE_TYPE_NOTFOUND);
        }

        page.setPageType(pageType);

        */

        page.setName(dto.getName());
        page.setShortDescription(dto.getShortDescription());
        page.setLongDescription(dto.getLongDescription());


        Page edited = this.pageRepository.update(page);
    }
    
}
