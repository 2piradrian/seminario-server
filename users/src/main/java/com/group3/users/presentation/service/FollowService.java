package com.group3.users.presentation.service;

import com.group3.config.PrefixedUUID;
import com.group3.entity.Follow;
import com.group3.entity.NotificationContent;
import com.group3.entity.PageContent;
import com.group3.entity.PageProfile;
import com.group3.entity.User;
import com.group3.entity.UserProfile;
import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import com.group3.users.data.repository.NotificationsRepository;
import com.group3.users.data.repository.NotificationsRepositoryI;
import com.group3.users.data.repository.PageProfileRepository;
import com.group3.users.data.repository.UserProfileRepository;
import com.group3.users.domain.dto.follow.mapper.FollowMapper;
import com.group3.users.domain.dto.follow.request.*;
import com.group3.users.domain.dto.follow.response.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class FollowService implements FollowServiceI {

    private final SecretKeyHelper secretKeyHelper;

    private final AuthService authService;

    private final FollowRepository followRepository;

    private final UserProfileRepository userProfileRepository;

    private final PageProfileRepository pageProfileRepository;

    private final NotificationsRepositoryI notificationsRepository;


    // ======== Toggle Follow ========

    @Override
    public void toggleFollow(ToggleFollowReq dto) {
        User user = this.authService.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.USER_NOT_FOUND);
        if (user.getId().equals(dto.getId())) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        // Check if already exists
        List<Follow> existing = this.followRepository.getAllFollowing(user.getId())
                .stream()
                .filter(f -> f.getFollowedId().equals(dto.getId()))
                .toList();

        if (!existing.isEmpty()) {
            followRepository.delete(existing.get(0).getId());
            return;
        }

        PrefixedUUID.EntityType type = PrefixedUUID.resolveType(UUID.fromString(dto.getId()));
        if (type == PrefixedUUID.EntityType.USER) {
            UserProfile profileToFollow = this.userProfileRepository.getById(dto.getId());
            if (profileToFollow == null) throw new ErrorHandler(ErrorType.USER_NOT_FOUND);
        }
        else if (type == PrefixedUUID.EntityType.PAGE) {
            PageProfile pageProfile = this.pageProfileRepository.getById(dto.getId(), dto.getToken());
            if (pageProfile == null) throw new ErrorHandler(ErrorType.PAGE_NOT_FOUND);
        }

        Follow follow = new Follow();
        follow.setFollowerId(user.getId());
        follow.setFollowedId(dto.getId());
        followRepository.save(follow);

        // Send notification
        notificationsRepository.create(
                secretKeyHelper.getSecret(),
                dto.getId(), // targetId: the user/page being followed
                user.getId(), // sourceId: the user who is following
                NotificationContent.FOLLOW.name()
        );
    }


    // ======== Get Followers ========

    @Override
    public GetFollowerPageRes getFollowers(GetFollowerPageReq dto) {
        User authUser = this.authService.auth(dto.getToken());
        if (authUser == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        UserProfile user = this.userProfileRepository.getById(dto.getUserId());
        if (user == null) throw new ErrorHandler(ErrorType.USER_NOT_FOUND);

        PageContent<Follow> followersPage = this.followRepository.findByFollowedId(dto.getUserId(), dto.getPage(), dto.getSize());

        List<String> followerIds = followersPage.getContent().stream()
                .map(Follow::getFollowerId)
                .toList();

        List<String> userIds = new ArrayList<>();
        List<String> pageIds = new ArrayList<>();

        for (String id : followerIds) {
            PrefixedUUID.EntityType type = PrefixedUUID.resolveType(UUID.fromString(id));
            if (type == PrefixedUUID.EntityType.USER) userIds.add(id);
            else if (type == PrefixedUUID.EntityType.PAGE) pageIds.add(id);
        }

        List<UserProfile> userProfiles = this.userProfileRepository.getListByIds(userIds);
        List<PageProfile> pageProfiles = this.pageProfileRepository.getListByIds(pageIds, secretKeyHelper.getSecret());

        List<String> followingIds = followRepository.getAllFollowing(authUser.getId()).stream().map(Follow::getFollowedId).toList();
        Set<String> followingSet = new HashSet<>(followingIds);

        for (UserProfile profile : userProfiles) profile.setIsFollowing(followingSet.contains(profile.getId()));
        for (PageProfile page : pageProfiles) page.setIsFollowing(followingSet.contains(page.getId()));

        List<Object> followers = new ArrayList<>();
        followers.addAll(userProfiles);
        followers.addAll(pageProfiles);

        return FollowMapper.getFollowerPage().toResponse(followersPage, followers);
    }


    // ======== Get following ========

    @Override
    public GetFollowingPageRes getFollowing(GetFollowingPageReq dto) {
        User authUser = this.authService.auth(dto.getToken());
        if (authUser == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        UserProfile user = this.userProfileRepository.getById(dto.getUserId());
        if (user == null) throw new ErrorHandler(ErrorType.USER_NOT_FOUND);

        PageContent<Follow> followingPage = followRepository.findByFollowerId(dto.getUserId(), dto.getPage(), dto.getSize());

        List<String> followingIds = followingPage.getContent().stream()
                .map(Follow::getFollowedId)
                .toList();

        List<String> userIds = new ArrayList<>();
        List<String> pageIds = new ArrayList<>();

        for (String id : followingIds) {
            PrefixedUUID.EntityType type = PrefixedUUID.resolveType(UUID.fromString(id));
            if (type == PrefixedUUID.EntityType.USER) userIds.add(id);
            else if (type == PrefixedUUID.EntityType.PAGE) pageIds.add(id);
        }

        List<UserProfile> userProfiles = this.userProfileRepository.getListByIds(userIds);
        List<PageProfile> pageProfiles = this.pageProfileRepository.getListByIds(pageIds, secretKeyHelper.getSecret());

        for (UserProfile profile : userProfiles) profile.setIsFollowing(true);
        for (PageProfile page : pageProfiles) page.setIsFollowing(true);

        List<Object> following = new ArrayList<>();
        following.addAll(userProfiles);
        following.addAll(pageProfiles);

        return FollowMapper.getFollowingPage().toResponse(followingPage, following);
    }


    // ======== Get Followers Count ========

    @Override
    public GetFollowersQuantityByIdRes getFollowersQuantityById(GetFollowersQuantityByIdReq dto) {
        if (!this.secretKeyHelper.isValid(dto.getSecret())) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        Integer followers = this.followRepository.getFollowersQuantity(dto.getId());
        return FollowMapper.getFollowersQuantityById().toResponse(followers);
    }

    // ======== Get Following Count ========

    @Override
    public GetFollowingQuantityByIdRes getFollowingQuantityById(GetFollowingQuantityByIdReq dto) {
        if (!this.secretKeyHelper.isValid(dto.getSecret())) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        Integer following = this.followRepository.getFollowingQuantity(dto.getId());
        return FollowMapper.getFollowingQuantityById().toResponse(following);
    }

    // ======== Get All Followers ========

    @Override
    public GetAllFollowersRes getAllFollowers(GetAllFollowersReq dto) {
        if (!this.secretKeyHelper.isValid(dto.getSecret())) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        List<Follow> followers = this.followRepository.getAllFollowers(dto.getId());
        return FollowMapper.getAllFollowers().toResponse(followers);
    }

    // ======== Get All Following ========

    @Override
    public GetAllFollowingRes getAllFollowing(GetAllFollowingReq dto) {
        if (!this.secretKeyHelper.isValid(dto.getSecret())) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        List<Follow> following = this.followRepository.getAllFollowing(dto.getId());
        return FollowMapper.getAllFollowing().toResponse(following);
    }
}
