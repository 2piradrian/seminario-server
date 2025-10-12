package com.group3.user_profiles.presentation.service;

import com.group3.config.PrefixedUUID;
import com.group3.entity.*;
import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import com.group3.user_profiles.config.helpers.SecretKeyHelper;
import com.group3.user_profiles.data.repository.*;
import com.group3.user_profiles.domain.dto.profile.mapper.UserProfileMapper;
import com.group3.user_profiles.domain.dto.profile.request.*;
import com.group3.user_profiles.domain.dto.profile.response.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class UserProfileService implements UserProfileServiceI {

    private final SecretKeyHelper secretKeyHelper;

    private final UserProfileRepository userProfileRepository;

    private final PageProfileRepository pageProfileRepository;

    private final CatalogRepository catalogRepository;

    private final UserRepository userRepository;

    private final ImagesRepository imagesRepository;

    @Override
    public GetFollowerPageRes getFollowers(GetFollowerPageReq dto) {
        UserProfile user = this.userProfileRepository.getById(dto.getUserId());
        if (user == null) throw new ErrorHandler(ErrorType.USER_NOT_FOUND);

        PageContent<String> followersPage =
                this.userProfileRepository.getFollowing(user.getId(), dto.getPage(), dto.getSize());

        List<String> userIds = new ArrayList<>();
        List<String> pageIds = new ArrayList<>();

        for (String id : followersPage.getContent()) {
            PrefixedUUID.EntityType type = PrefixedUUID.resolveType(UUID.fromString(id));
            if (type == PrefixedUUID.EntityType.USER) {
                userIds.add(id);
            }
            else if (type == PrefixedUUID.EntityType.PAGE) {
                pageIds.add(id);
            }
        }

        List<PageProfile> pageProfiles = this.pageProfileRepository.getListByIds(pageIds, secretKeyHelper.getSecret());
        List<UserProfile> userProfiles = this.userProfileRepository.getListByIds(userIds);

        List<Object> followers = new ArrayList<>();
        followers.addAll(userProfiles);
        followers.addAll(pageProfiles);

        return UserProfileMapper.getFollowerPage().toResponse(followersPage, followers);
    }

    @Override
    public void create(CreateUserProfileReq dto) {

        if (!this.secretKeyHelper.isValid(dto.getSecret())) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        UserProfile userProfile = new UserProfile();

        userProfile.setId(dto.getId());
        userProfile.setName(dto.getName());
        userProfile.setSurname(dto.getSurname());
        userProfile.setEmail(dto.getEmail());

        userProfile.setMemberSince(LocalDateTime.now());

        userProfile.setPortraitImage("");
        userProfile.setProfileImage("");
        userProfile.setShortDescription("¡New user!");
        userProfile.setLongDescription("¡New user!");
        userProfile.setInstruments(List.of());
        userProfile.setStyles(List.of());

        this.userProfileRepository.save(userProfile);
    }

    @Override
    public GetUserProfileByIdRes getById(GetUserProfileByIdReq dto) {
        UserProfile userProfile = this.userProfileRepository.getById(dto.getUserId());
        if (userProfile == null) throw new ErrorHandler(ErrorType.USER_NOT_FOUND);

        return UserProfileMapper.getById().toResponse(userProfile);
    }

    @Override
    public void update(EditUserProfileReq dto) {
        User user = this.userRepository.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.USER_NOT_FOUND);

        UserProfile userProfile = this.userProfileRepository.getByEmail(user.getEmail());

        if (userProfile == null){
            throw new ErrorHandler(ErrorType.USER_NOT_FOUND);
        }

        if (!dto.getStyles().isEmpty()){
            List<Style> styles = this.catalogRepository.getStyleListById(dto.getStyles().stream().map(Style::getId).toList());
            userProfile.setStyles(styles);
        }

        if (!dto.getInstruments().isEmpty()){
            List<Instrument> instruments = this.catalogRepository.getInstrumentListById(dto.getInstruments().stream().map(Instrument::getId).toList());
            userProfile.setInstruments(instruments);
        }

        if (dto.getProfileImage() != null){
            String profileImage = userProfile.getProfileImage();
            if (profileImage != null && !profileImage.isEmpty()) {
                this.imagesRepository.delete(profileImage, secretKeyHelper.getSecret());
            }

            String profileId = this.imagesRepository.upload(dto.getProfileImage(), secretKeyHelper.getSecret());
            userProfile.setProfileImage(profileId);
        }

        if (dto.getPortraitImage() != null){
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

    @Override
    public GetOwnUserProfileRes getOwnProfile(GetOwnUserProfileReq dto){
        User user = this.userRepository.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.USER_NOT_FOUND);

        UserProfile userProfile = this.userProfileRepository.getByEmail(user.getEmail());

        if (userProfile == null){
            throw new ErrorHandler(ErrorType.USER_NOT_FOUND);
        }

        List<Style> styles = this.catalogRepository.getStyleListById(userProfile.getStyles().stream().map(Style::getId).toList());
        userProfile.setStyles(styles);

        List<Instrument> instruments = this.catalogRepository.getInstrumentListById(userProfile.getInstruments().stream().map(Instrument::getId).toList());
        userProfile.setInstruments(instruments);

        return UserProfileMapper.getOwnProfile().toResponse(userProfile);
    }

    @Override
    public void delete(DeleteUserProfileReq dto) {
        User user = this.userRepository.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.USER_NOT_FOUND);

        UserProfile userProfile = this.userProfileRepository.getByEmail(user.getEmail());
        user.setStatus(Status.DELETED);

        this.userProfileRepository.update(userProfile);
    }

}
