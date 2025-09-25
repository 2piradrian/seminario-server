package com.group3.profiles.presentation.service;

import com.group3.entity.*;
import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import com.group3.profiles.data.repository.CatalogRepository;
import com.group3.profiles.data.repository.UserProfileProfileRepository;
import com.group3.profiles.domain.dto.profile.mapper.UserProfileMapper;
import com.group3.profiles.domain.dto.profile.request.*;
import com.group3.profiles.domain.dto.profile.response.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class ProfileService implements ProfileServiceI {

    private final UserProfileProfileRepository userProfileRepository;

    private final CatalogRepository catalogRepository;

    private final AuthService authService;

    @Override
    public GetUserProfileByIdRes getById(GetUserProfileByIdReq dto) {
        User user = this.userProfileRepository.getById(dto.getUserId());
        if (user == null) throw new ErrorHandler(ErrorType.USER_NOT_FOUND);

        return UserProfileMapper.getById().toResponse(user);
    }

    public EditUserProfileRes update(EditUserProfileReq dto) {
        AuthUserRes authResponse = this.authService.auth(AuthUserReq.create(dto.getToken()));

        User user = this.userProfileRepository.getByEmail(authResponse.getEmail());

        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setPortraitImage(dto.getPortraitImage());
        user.setProfileImage(dto.getProfileImage());
        user.setShortDescription(dto.getShortDescription());
        user.setLongDescription(dto.getLongDescription());
        user.setStyles(this.catalogRepository.getStyleListById(dto.getStyles().stream().map(Style::getId).toList()));
        user.setInstruments(this.catalogRepository.getInstrumentListById(dto.getInstruments().stream().map(Instrument::getId).toList()));

        User edited = this.userProfileRepository.update(user);
        return UserProfileMapper.update().toResponse(edited);
    }

    @Override
    public void delete(DeleteUserProfileReq dto) {
        AuthUserRes authResponse = this.authService.auth(AuthUserReq.create(dto.getToken()));

        User user = this.userProfileRepository.getByEmail(authResponse.getEmail());

        user.setStatus(Status.DELETED);

        this.userProfileRepository.update(user);
    }

    @Override
    public GetOwnUserProfileRes getOwnProfile(GetOwnUserProfileReq dto){
        AuthUserRes authResponse = this.authService.auth(AuthUserReq.create(dto.getToken()));

        User user = this.userProfileRepository.getByEmail(authResponse.getEmail());

        List<Style> styles = this.catalogRepository.getStyleListById(user.getStyles().stream().map(Style::getId).toList());
        user.setStyles(styles);

        List<Instrument> instruments = this.catalogRepository.getInstrumentListById(user.getInstruments().stream().map(Instrument::getId).toList());
        user.setInstruments(instruments);

        return UserProfileMapper.getOwnProfile().toResponse(user);
    }

}
