package com.group3.users.presentation.service;

import com.group3.entity.*;
import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import com.group3.users.config.helpers.SecretKeyHelper;
import com.group3.users.data.repository.*;
import com.group3.users.domain.dto.auth.request.AuthUserReq;
import com.group3.users.domain.dto.auth.response.AuthUserRes;
import com.group3.users.domain.dto.follow.request.GetAllFollowersReq;
import com.group3.users.domain.dto.follow.request.GetAllFollowingReq;
import com.group3.users.domain.dto.user.mapper.UserMapper;
import com.group3.users.domain.dto.user.mapper.implementation.GetByListOfIdsPageMapper;
import com.group3.users.domain.dto.user.request.*;
import com.group3.users.domain.dto.user.response.*;
import com.group3.users.domain.repository.NotificationsRepositoryI;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class UserService implements UserServiceI {

    private final UserRepository userRepository;

    private final SecretKeyHelper secretKeyHelper;

    private final UserProfileRepository userProfileRepository;

    private final CatalogRepository catalogRepository;

    private final ImagesRepository imagesRepository;

    private final AuthService authService;

    private final FollowService followService;

    private final NotificationsRepository notificationsRepository;

    private final ReviewRepository reviewRepository;

    private final FollowRepository followRepository;

    private final ChatRepository chatRepository;

    private final PageProfileRepository pageProfileRepository;

    private final CommentsRepository commentsRepository;

    private final PostsRepository postsRepository;

    private final EventsRepository eventsRepository;

    @Override

    public GetUserByIdRes getById(GetUserByIdReq dto) {
        User user = this.authService.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        User userResult = this.userRepository.getById(dto.getUserId());
        if (userResult == null) throw new ErrorHandler(ErrorType.USER_NOT_FOUND);

        UserProfile profileResult = userResult.getProfile();

        List<Style> styles = this.catalogRepository.getStyleListById(profileResult.getStyles().stream().map(Style::getId).toList());
        profileResult.setStyles(styles);

        List<Instrument> instruments = this.catalogRepository.getInstrumentListById(profileResult.getInstruments().stream().map(Instrument::getId).toList());
        profileResult.setInstruments(instruments);

        List<String> following = this.followService.getAllFollowing(
                GetAllFollowingReq.create(userResult.getId(), secretKeyHelper.getSecret())
        ).getFollowing().stream().map(Follow::getFollowedId).toList();

        List<String> followers = this.followService.getAllFollowers(
                GetAllFollowersReq.create(userResult.getId(), secretKeyHelper.getSecret())
        ).getFollowers().stream().map(Follow::getFollowerId).toList();

        profileResult.isOwnProfile(user.getId());
        profileResult.setFollowsChecks(user.getId(), following, followers);

        userResult.setProfile(profileResult);

        return UserMapper.getById().toResponse(userResult);
    }

    @Override
    public GetAllStaffRes getAllStaff(GetAllStaffReq dto){
        AuthUserRes auth = this.authService.auth(AuthUserReq.create(dto.getToken()));
        if (auth == null) throw new ErrorHandler(ErrorType.USER_NOT_FOUND);

        if (!auth.getRole().canAsignRole()) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        List<User> staffUsers = this.userRepository.getAllStaff();

        return UserMapper.getAllStaff().toResponse(staffUsers);
    }

    @Override
    public GetUserMutualsFollowersRes getMutualsFollowers(GetUserMutualsFollowersReq dto){
        User user = this.authService.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        List<User> mutualsFollowers = this.userRepository.getMutualsFollowers(dto.getUserId());

        for (User mutualFollower : mutualsFollowers) {
            UserProfile profileResult = mutualFollower.getProfile();

            List<String> following = this.followService.getAllFollowing(
                GetAllFollowingReq.create(mutualFollower.getId(), secretKeyHelper.getSecret())
            ).getFollowing().stream().map(Follow::getFollowedId).toList();

            List<String> followers = this.followService.getAllFollowers(
                GetAllFollowersReq.create(mutualFollower.getId(), secretKeyHelper.getSecret())
            ).getFollowers().stream().map(Follow::getFollowerId).toList();

            profileResult.isOwnProfile(user.getId());
            profileResult.setFollowsChecks(user.getId(), following, followers);

            mutualFollower.setProfile(profileResult);
        }

        return UserMapper.getMutualsFollowers().toResponse(mutualsFollowers);
    }

    @Override
    public void delete(DeleteUserReq dto) {
        AuthUserRes auth = this.authService.auth(AuthUserReq.create(dto.getToken()));
        if (auth == null) throw new ErrorHandler(ErrorType.USER_NOT_FOUND);

        User user = this.userRepository.getById(auth.getId());
        if (user == null) throw new ErrorHandler(ErrorType.USER_NOT_FOUND);

        if (!user.canDelete(user)) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        // ======== Delete Images ========
        String profileImage = user.getProfile().getProfileImage();
        if (profileImage != null && !profileImage.isEmpty()) {
            this.imagesRepository.delete(profileImage, secretKeyHelper.getSecret());
        }
        String portraitImage = user.getProfile().getPortraitImage();
        if (portraitImage != null && !portraitImage.isEmpty()) {
            this.imagesRepository.delete(portraitImage, secretKeyHelper.getSecret());
        }

        // ======== Delete Reviews ========
        this.reviewRepository.deleteByReviewerId(user.getId());
        this.reviewRepository.deleteByReviewedId(user.getId());

        // ======== Delete Follows ========
        this.followRepository.deleteByFollowerId(user.getId());
        this.followRepository.deleteByFollowedId(user.getId());

        // ======== Delete Notifications ========
        this.notificationsRepository.deleteBySourceId(dto.getToken(), this.secretKeyHelper.getSecret(), user.getId());

        // ======== Delete Chats ========
        this.chatRepository.deleteUserHistory(user.getId(), this.secretKeyHelper.getSecret());

        // ======== Delete Comments ========
        this.commentsRepository.deleteCommentsByUserId(dto.getToken(), user.getId(), this.secretKeyHelper.getSecret());

        // ======== Delete Pages and Participants ========
        this.pageProfileRepository.deleteUserPages(user.getId(), this.secretKeyHelper.getSecret());

        // ======== Delete Events ========
        this.eventsRepository.deleteByUserId(this.secretKeyHelper.getSecret(), user.getId());

        // ======== Delete Posts ========
        this.postsRepository.deletePostsByUserId(dto.getToken(), user.getId(), this.secretKeyHelper.getSecret());

        // ======== Delete User ========
        this.userRepository.delete(user);
    }

    @Override
    public void deleteById(String token, String id) {
        User user = this.userRepository.getById(id);
        if (user == null) throw new ErrorHandler(ErrorType.USER_NOT_FOUND);

        if (!user.canDelete(user)) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        // ======== Delete Images ========
        String profileImage = user.getProfile().getProfileImage();
        if (profileImage != null && !profileImage.isEmpty()) {
            this.imagesRepository.delete(profileImage, secretKeyHelper.getSecret());
        }
        String portraitImage = user.getProfile().getPortraitImage();
        if (portraitImage != null && !portraitImage.isEmpty()) {
            this.imagesRepository.delete(portraitImage, secretKeyHelper.getSecret());
        }

        // ======== Delete Reviews ========
        this.reviewRepository.deleteByReviewerId(user.getId());
        this.reviewRepository.deleteByReviewedId(user.getId());

        // ======== Delete Follows ========
        this.followRepository.deleteByFollowerId(user.getId());
        this.followRepository.deleteByFollowedId(user.getId());

        // ======== Delete Notifications ========
        this.notificationsRepository.deleteBySourceId(token, this.secretKeyHelper.getSecret(), user.getId());

        // ======== Delete Chats ========
        this.chatRepository.deleteUserHistory(user.getId(), this.secretKeyHelper.getSecret());

        // ======== Delete Comments ========
        this.commentsRepository.deleteCommentsByUserId(token, user.getId(), this.secretKeyHelper.getSecret());

        // ======== Delete Pages and Participants ========
        this.pageProfileRepository.deleteUserPages(user.getId(), this.secretKeyHelper.getSecret());

        // ======== Delete Events ========
        this.eventsRepository.deleteByUserId(this.secretKeyHelper.getSecret(), user.getId());

        // ======== Delete Posts ========
        this.postsRepository.deletePostsByUserId(token, user.getId(), this.secretKeyHelper.getSecret());

        // ======== Delete User ========
        this.userRepository.delete(user);
    }

    // ======== Get User Profile Page Filtered ========

    @Override
    public GetUserPageFilteredRes getProfileFiltered(GetUserPageFilteredReq dto) {
        User user = this.authService.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        if (!this.secretKeyHelper.isValid(dto.getSecret())) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        PageContent<User> profiles = this.userRepository.getFilteredPage(
            dto.getFullname(),
            dto.getStyles(),
            dto.getInstruments(),
            dto.getPage(),
            dto.getSize()
        );

        for (User userResult : profiles.getContent()) {
            UserProfile profileResult = userResult.getProfile();

            List<String> following = this.followService.getAllFollowing(
                    GetAllFollowingReq.create(userResult.getId(), secretKeyHelper.getSecret())
            ).getFollowing().stream().map(Follow::getFollowedId).toList();

            List<String> followers = this.followService.getAllFollowers(
                    GetAllFollowersReq.create(userResult.getId(), secretKeyHelper.getSecret())
            ).getFollowers().stream().map(Follow::getFollowerId).toList();

            profileResult.isOwnProfile(user.getId());
            profileResult.setFollowsChecks(user.getId(), following, followers);

            userResult.setProfile(profileResult);
        }

        return UserMapper.getFiltered().toResponse(profiles);
    }

    @Override
    public GetByListOfIdsPageRes getByListOfIds(GetByListOfIdsPageReq dto) {
        User user = this.authService.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        if (!this.secretKeyHelper.isValid(dto.getSecret())) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        PageContent<User> users = this.userRepository.getByListOfIds(
                dto.getIds(),
                dto.getPage(),
                dto.getSize()
        );

        for (User userResult : users.getContent()) {
            userResult.setPassword(null);
            UserProfile profileResult = userResult.getProfile();
            profileResult.isOwnProfile(user.getId());
            userResult.setProfile(profileResult);
        }

        return UserMapper.getByListOfIdsPage().toResponse(users);
    }

    // ======== Update User Profile ========

    @Override
    public void update(EditUserReq dto) {
        User user = this.authService.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.USER_NOT_FOUND);

        UserProfile userProfile = user.getProfile();

        // ======== Update Styles and Instruments ========
        if (!dto.getStyles().isEmpty()) {
            List<Style> styles = this.catalogRepository.getStyleListById(dto.getStyles().stream().map(Style::getId).toList());
            userProfile.setStyles(styles);
        }

        if (!dto.getInstruments().isEmpty()) {
            List<Instrument> instruments = this.catalogRepository.getInstrumentListById(dto.getInstruments().stream().map(Instrument::getId).toList());
            userProfile.setInstruments(instruments);
        }

        // ======== Update Profile Image ========
        if (dto.getProfileImage() != null) {
            String profileImage = userProfile.getProfileImage();
            if (profileImage != null && !profileImage.isEmpty()) {
                this.imagesRepository.delete(profileImage, secretKeyHelper.getSecret());
            }
            String profileId = this.imagesRepository.upload(dto.getProfileImage(), secretKeyHelper.getSecret());
            userProfile.setProfileImage(profileId);
        }

        // ======== Update Portrait Image ========
        if (dto.getPortraitImage() != null) {
            String portraitImage = userProfile.getPortraitImage();
            if (portraitImage != null && !portraitImage.isEmpty()) {
                this.imagesRepository.delete(portraitImage, secretKeyHelper.getSecret());
            }
            String portraitId = this.imagesRepository.upload(dto.getPortraitImage(), secretKeyHelper.getSecret());
            userProfile.setPortraitImage(portraitId);
        }

        userProfile.setName(dto.getName());
        userProfile.setSurname(dto.getSurname());
        userProfile.setShortDescription(dto.getShortDescription());
        userProfile.setLongDescription(dto.getLongDescription());

        this.userProfileRepository.update(userProfile);
    }

}
