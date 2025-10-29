package com.group3.users.presentation.service;

import com.group3.entity.*;
import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import com.group3.users.config.helpers.SecretKeyHelper;
import com.group3.users.data.repository.*;
import com.group3.users.domain.dto.auth.request.AuthUserReq;
import com.group3.users.domain.dto.auth.response.AuthUserRes;
import com.group3.users.domain.dto.user.mapper.UserMapper;
import com.group3.users.domain.dto.user.request.*;
import com.group3.users.domain.dto.user.response.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class UserService implements UserServiceI {

    private final UserRepository userRepository;

    private final SecretKeyHelper secretKeyHelper;

    private final UserProfileRepository userProfileRepository;

    private final PageProfileRepository pageProfileRepository;

    private final CatalogRepository catalogRepository;

    private final ImagesRepository imagesRepository;

    private final AuthService authService;

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

        // TODO: use profileResult.setFollowsCheck()

        //Integer followersCount = this.userProfileRepository.getFollowersCount(profileResult.getId());
        //profileResult.setFollowersQuantity(followersCount);
        //
        //Integer followingCount = this.userProfileRepository.getFollowingCount(profileResult.getId());
        //profileResult.setFollowingQuantity(followingCount);
        //
        //Boolean ownProfile = user.getId().equals(userResult.getProfile().getId());
        //Boolean isFollowing = userResult.getProfile().getFollowing().contains(user.getId());

        return UserMapper.getById().toResponse(user);
    }

    @Override
    public GetAllStaffRes getAllStaff(GetAllStaffReq dto){
        AuthUserRes auth = this.authService.auth(AuthUserReq.create(dto.getToken()));
        if (auth == null) throw new ErrorHandler(ErrorType.USER_NOT_FOUND);

        if (!auth.getRole().canAsignRole()) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        List<User> staffUsers = this.userRepository.getAllStaff();

        Map<Role,List<UserProfile>> staff = new HashMap<>();
        List<UserProfile> modUsers = new ArrayList<>();
        List<UserProfile> adminUsers = new ArrayList<>();

        for (User user : staffUsers){
            UserProfile userProfile = this.userProfileRepository.getById(user.getId());
            if (user.getRole().equals(Role.MODERATOR)){
                modUsers.add(userProfile);
            } else if (user.getRole().equals(Role.ADMIN)){
                adminUsers.add(userProfile);
            }
        }

        staff.put(Role.ADMIN,adminUsers);
        staff.put(Role.MODERATOR,modUsers);

        return UserMapper.getAllStaff().toResponse(staff);
    }

    @Override
    public void delete(DeleteUserReq dto) {
        AuthUserRes auth = this.authService.auth(AuthUserReq.create(dto.getToken()));
        if (auth == null) throw new ErrorHandler(ErrorType.USER_NOT_FOUND);

        User user = this.userRepository.getById(auth.getId());
        if (user == null) throw new ErrorHandler(ErrorType.USER_NOT_FOUND);

        user.setStatus(Status.DELETED);

        this.userRepository.save(user);
    }

    // ======== Get User Profile Page Filtered ========

    @Override
    public GetUserPageFilteredRes getProfileFiltered(GetUserPageFilteredReq dto) {
        if (!this.secretKeyHelper.isValid(dto.getSecret())) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        PageContent<User> profiles = this.userRepository.getFilteredPage(
            dto.getFullname(),
            dto.getStyles(),
            dto.getInstruments(),
            dto.getPage(),
            dto.getSize()
        );

        return UserMapper.getFiltered().toResponse(profiles);
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
