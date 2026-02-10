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
import com.group3.utils.Verse;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    private final NotificationRepository notificationRepository;

    private final PostsRepository postsRepository;

    private final CommentsRepository commentsRepository;

    private final EventsRepository eventsRepository;


    // ======== Create Page ========

    @Override
    public CreatePageRes create(CreatePageReq dto) {

        User user = this.userRepository.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        PageProfile existsPage = this.pageProfileRepository.getByName(dto.getName());
        if (existsPage != null) throw new ErrorHandler(ErrorType.PAGENAME_ALREADY_EXISTS);

        PageProfile page = new PageProfile();
        page.setId(PrefixedUUID.generate(PrefixedUUID.EntityType.PAGE).toString());

        PageType pageType = this.catalogRepository.getById(dto.getPageTypeId());
        if(pageType == null) throw new ErrorHandler(ErrorType.PAGE_TYPE_NOT_FOUND);
        page.setPageType(pageType);

        page.setOwner(user);
        page.setMembers(List.of(user));

        Verse verse = new Verse();

        page.setName(dto.getName());
        page.setPortraitImage("");
        page.setProfileImage("");
        page.setShortDescription(verse.getRandomVerse());
        page.setLongDescription(verse.getRandomVerse());
        page.setStatus(Status.ACTIVE);
        page.setCreatedAt(LocalDateTime.now());

        PageProfile newPage = this.pageProfileRepository.save(page);
        return PageMapper.create().toResponse(newPage);
    }


    // ======== Get Page by ID ========

    @Override
    public GetPageByIdRes getById(GetPageByIdReq dto) {
        User user = this.userRepository.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.USER_NOT_FOUND);

        PageProfile page = this.pageProfileRepository.getById(dto.getPageId());
        if (page == null) throw new ErrorHandler(ErrorType.PAGE_NOT_FOUND);

        List<Follow> follows = this.userRepository.getAllFollowers(dto.getPageId(), this.secretKeyHelper.getSecret());
        if (follows == null) throw new ErrorHandler(ErrorType.PAGE_NOT_FOUND);

        List<User> members = new ArrayList<>(List.of());
        for (User member : page.getMembers()){
            if (member == null || member.getId() == null) throw new ErrorHandler(ErrorType.USER_NOT_FOUND);

            User completeMember = this.userRepository.getById(dto.getToken(), member.getId());
            if (completeMember == null) throw new ErrorHandler(ErrorType.USER_NOT_FOUND);

            members.add(completeMember);
        }
        page.setMembers(members);

        Boolean isFollowing = follows.stream().anyMatch(follow -> follow.getFollowerId().equals(user.getId()));

        return PageMapper.getPage().toResponse(page, follows.size(), isFollowing);
    }


    // ======== Get Page Profile Page Filtered ========

    @Override
    public GetPageProfilePageFilteredRes getProfileFiltered(GetPageProfilePageFilteredReq dto) {
        User user = userRepository.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        if (!this.secretKeyHelper.isValid(dto.getSecret())) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        PageContent<PageProfile> pages = this.pageProfileRepository.getFilteredPage(
            dto.getName(),
            dto.getPageTypeId(),
            dto.getPage(),
            dto.getSize()
        );

        for (PageProfile pageResult : pages.getContent()) {

            List<Follow> follows = this.userRepository.getAllFollowers(pageResult.getId(), this.secretKeyHelper.getSecret());
            if (follows == null) throw new ErrorHandler(ErrorType.PAGE_NOT_FOUND);

            Boolean isFollowing = follows.stream().anyMatch(follow -> follow.getFollowerId().equals(user.getId()));

            pageResult.setIsFollowing(isFollowing);
            
        }

        return PageMapper.getFiltered().toResponse(pages);
    }

    // ======== Get Pages by User ========

    @Override
    public GetPageByUserIdRes getUserPages(GetPageByUserIdReq dto) {
        User user = this.userRepository.getById(dto.getToken(), dto.getUserId());
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
            if (profileImage != null && !profileImage.isEmpty()) {
                this.imagesRepository.delete(profileImage, secretKeyHelper.getSecret());
            }
            String profileId = this.imagesRepository.upload(dto.getProfileImage(), secretKeyHelper.getSecret());
            page.setProfileImage(profileId);
        }

        // ======== Update Portrait Image ========
        if (dto.getPortraitImage() != null) {
            String portraitImage = page.getPortraitImage();
            if (portraitImage != null && !portraitImage.isEmpty()) {
                this.imagesRepository.delete(portraitImage, secretKeyHelper.getSecret());
            }
            String portraitId = this.imagesRepository.upload(dto.getPortraitImage(), secretKeyHelper.getSecret());
            page.setPortraitImage(portraitId);
        }

        // ======== Validate and Update Members ========

        List<User> newMembersList = dto.getMembers().stream()
            .map(memberId -> {
                User userProfile = this.userRepository.getById(dto.getToken(), memberId);
                if (userProfile == null) throw new ErrorHandler(ErrorType.USER_NOT_FOUND);
                return userProfile;
            })
            .toList();
        List<String> newMembersListId = newMembersList.stream()
            .map(User::getId)
            .toList();

        List<User> currentMembers = page.getMembers();
        List<String> currentMemberIds = currentMembers.stream()
            .map(User::getId)
            .toList();

        newMembersList.forEach(newMember -> {
            if (!currentMemberIds.contains(newMember.getId())){

                this.notificationRepository.create(
                    this.secretKeyHelper.getSecret(),
                    newMember.getId(),
                    page.getId(),
                    user.getId(),
                    NotificationContent.PAGE_INVITATION.name()
                );

            }

        });

        List<User> membersToKeep = currentMembers.stream()
            .filter(member -> newMembersListId.contains(member.getId()))
            .toList();

        page.setMembers(membersToKeep);

        // ======== Update Page Type and Metadata ========

        PageType pageType = this.catalogRepository.getById(dto.getPageTypeId());
        if(pageType == null) throw new ErrorHandler(ErrorType.PAGE_TYPE_NOT_FOUND);
        page.setPageType(pageType);

        page.setName(dto.getName());
        page.setShortDescription(dto.getShortDescription());
        page.setLongDescription(dto.getLongDescription());

        this.pageProfileRepository.update(page);
    }

    @Override
    public void joinPage(JoinPageReq dto) {
        User user = this.userRepository.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        PageProfile page = this.pageProfileRepository.getById(dto.getPageId());
        if (page == null) throw new ErrorHandler(ErrorType.PAGE_NOT_FOUND);
        if (page.getOwner().getId().equals(user.getId())) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        // ======== Validate and Update Member ========

        Notification notification = this.notificationRepository.getLatestUncheckNotification(
            dto.getToken(),
            this.secretKeyHelper.getSecret(),
            user.getId(),
            page.getId()
        );

        if (notification == null){
            throw new ErrorHandler(ErrorType.INVITATION_ALREADY_USED);
        }

        this.notificationRepository.checkInvitation(
            dto.getToken(),
            this.secretKeyHelper.getSecret(),
            notification.getId()
        );

        // ======== Validate and Update Member ========

        List<User> newMembersList = new ArrayList<>(page.getMembers());

        boolean alreadyExists = newMembersList.stream()
            .anyMatch(member -> member.getId().equals(user.getId()));

        if (alreadyExists){
            throw new ErrorHandler(ErrorType.USER_ALREADY_MEMBER);
        }

        newMembersList.add(user);

        page.setMembers(newMembersList);

        this.pageProfileRepository.update(page);
    }

    @Override
    public void leavePage(LeavePageReq dto) {
        User user = this.userRepository.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        PageProfile page = this.pageProfileRepository.getById(dto.getPageId());
        if (page == null) throw new ErrorHandler(ErrorType.PAGE_NOT_FOUND);
        if (page.getOwner().getId().equals(user.getId())) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        // ======== Validate and Update Member ========

        List<User> members = page.getMembers();

        boolean alreadyExists = members.stream()
            .anyMatch(member -> member.getId().equals(user.getId()));

        if (!alreadyExists){
            throw new ErrorHandler(ErrorType.USER_NOT_MEMBER);
        }

        members.removeIf(m -> m.getId().equals(user.getId()));

        page.setMembers(members);

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

        if (page.getProfileImage() != null && !page.getProfileImage().isEmpty()) {
            this.imagesRepository.delete(page.getProfileImage(), secretKeyHelper.getSecret());
        }

        if (page.getPortraitImage() != null && !page.getPortraitImage().isEmpty()) {
            this.imagesRepository.delete(page.getPortraitImage(), secretKeyHelper.getSecret());
        }

        this.postsRepository.deletePostsByPageId(page.getId(), secretKeyHelper.getSecret());

        this.commentsRepository.deleteCommentsByPageId(page.getId(), secretKeyHelper.getSecret());

        this.eventsRepository.deleteEventsByPageId(page.getId(), secretKeyHelper.getSecret());

        this.pageProfileRepository.delete(page.getId());

        this.notificationRepository.deleteBySourceId(dto.getToken(), this.secretKeyHelper.getSecret(), page.getId());
    }

    @Override
    public void deleteUserPages(DeleteUserPagesReq dto) {
        if (!this.secretKeyHelper.isValid(dto.getSecret())) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        this.pageProfileRepository.deleteByOwnerId(dto.getUserId());
        
        this.pageProfileRepository.removeMemberFromAllPages(dto.getUserId());
    }

    @Override
    public GetPageGrowthReportRes getGrowthReport(GetPageGrowthReportReq dto) {
        User user = this.userRepository.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        boolean isSecretValid = this.secretKeyHelper.isValid(dto.getSecret());
        if (!isSecretValid) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        LocalDateTime now = LocalDateTime.now();

        LocalDateTime lastYear = now.minusYears(1);
        LocalDateTime lastMonth = now.minusMonths(1);
        LocalDateTime lastWeek = now.minusWeeks(1);

        TimeReportContent reportContent = this.pageProfileRepository.getGrowthReport(
            lastYear,
            lastMonth,
            lastWeek
        );

        return PageMapper.getPageGrowthReport().toResponse(reportContent);
    }

}
