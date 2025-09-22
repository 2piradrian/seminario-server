package com.group3.users.presentation.service;

import com.group3.entity.*;
import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import com.group3.users.config.helpers.AuthHelper;
import com.group3.users.data.repository.CatalogRepository;
import com.group3.users.data.repository.UserRepository;
import com.group3.users.domain.dto.auth.request.AuthUserReq;
import com.group3.users.domain.dto.auth.response.AuthUserRes;
import com.group3.users.domain.dto.user.mapper.UserMapper;
import com.group3.users.domain.dto.user.request.*;
import com.group3.users.domain.dto.user.response.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class UserService implements UserServiceI {

    private final UserRepository userRepository;

    private final CatalogRepository catalogRepository;

    private final AuthService authService;

    @Override
    public GetUserByIdRes getById(GetUserByIdReq dto) {
        User user = this.userRepository.getById(dto.getUserId());
        if (user == null) throw new ErrorHandler(ErrorType.USER_NOT_FOUND);

        return UserMapper.getById().toResponse(user);
    }

    public EditUserRes update(EditUserReq dto) {
        AuthUserRes authResponse = this.authService.auth(AuthUserReq.create(dto.getToken()));

        User user = this.userRepository.getByEmail(authResponse.getEmail());

        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setPortraitImage(dto.getPortraitImage());
        user.setProfileImage(dto.getProfileImage());
        user.setShortDescription(dto.getShortDescription());
        user.setLongDescription(dto.getLongDescription());
        user.setStyles(this.catalogRepository.getStyleListById(dto.getStyles()));
        user.setInstruments(this.catalogRepository.getInstrumentListById(dto.getInstruments()));

        User edited = this.userRepository.update(user);
        return UserMapper.update().toResponse(edited);
    }

    @Override
    public void delete(DeleteUserReq dto) {
        AuthUserRes authResponse = this.authService.auth(AuthUserReq.create(dto.getToken()));

        User user = this.userRepository.getByEmail(authResponse.getEmail());

        user.setStatus(Status.DELETED);

        this.userRepository.update(user);
    }

}
