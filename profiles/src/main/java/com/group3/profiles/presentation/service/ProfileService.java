package com.group3.profiles.presentation.service;

import com.group3.entity.*;
import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import com.group3.profiles.config.helpers.SecretKeyHelper;
import com.group3.profiles.data.repository.CatalogRepository;
import com.group3.profiles.data.repository.UserProfileRepository;
import com.group3.profiles.data.repository.UserRepository;
import com.group3.profiles.domain.dto.profile.mapper.UserProfileMapper;
import com.group3.profiles.domain.dto.profile.request.*;
import com.group3.profiles.domain.dto.profile.response.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class ProfileService implements ProfileServiceI {

    private final SecretKeyHelper secretKeyHelper;

    private final UserProfileRepository userProfileRepository;

    private final CatalogRepository catalogRepository;

    private final UserRepository userRepository;

    @Override
    public void create(CreateUserProfileReq dto) {

        if (!this.secretKeyHelper.isValid(dto.getSecret())) {
            log.info(dto.getSecret());
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

        userProfile.setName(dto.getName());
        userProfile.setSurname(dto.getSurname());
        userProfile.setPortraitImage(dto.getPortraitImage());
        userProfile.setProfileImage(dto.getProfileImage());
        userProfile.setShortDescription(dto.getShortDescription());
        userProfile.setLongDescription(dto.getLongDescription());
        userProfile.setStyles(this.catalogRepository.getStyleListById(dto.getStyles().stream().map(Style::getId).toList()));
        userProfile.setInstruments(this.catalogRepository.getInstrumentListById(dto.getInstruments().stream().map(Instrument::getId).toList()));

        this.userProfileRepository.update(userProfile);
    }

    @Override
    public void delete(DeleteUserProfileReq dto) {
        User user = this.userRepository.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.USER_NOT_FOUND);

        UserProfile userProfile = this.userProfileRepository.getByEmail(user.getEmail());
        user.setStatus(Status.DELETED);

        this.userProfileRepository.update(userProfile);
    }

    @Override
    public GetOwnUserProfileRes getOwnProfile(GetOwnUserProfileReq dto){
        User user = this.userRepository.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.USER_NOT_FOUND);

        UserProfile userProfile = this.userProfileRepository.getByEmail(user.getEmail());

        List<Style> styles = this.catalogRepository.getStyleListById(userProfile.getStyles().stream().map(Style::getId).toList());
        userProfile.setStyles(styles);

        List<Instrument> instruments = this.catalogRepository.getInstrumentListById(userProfile.getInstruments().stream().map(Instrument::getId).toList());
        userProfile.setInstruments(instruments);

        return UserProfileMapper.getOwnProfile().toResponse(userProfile);
    }

}
