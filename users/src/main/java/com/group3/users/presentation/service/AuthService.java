package com.group3.users.presentation.service;

import com.group3.entity.Role;
import com.group3.entity.Status;
import com.group3.entity.Token;
import com.group3.entity.User;
import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import com.group3.users.config.helpers.AuthHelper;
import com.group3.users.data.repository.UserRepository;
import com.group3.users.domain.dto.auth.mapper.AuthMapper;
import com.group3.users.domain.dto.auth.request.AuthUserReq;
import com.group3.users.domain.dto.auth.request.LoginUserReq;
import com.group3.users.domain.dto.auth.request.RegisterUserReq;
import com.group3.users.domain.dto.auth.response.AuthUserRes;
import com.group3.users.domain.dto.auth.response.LoginUserRes;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class AuthService implements AuthServiceI {

    private final AuthHelper authHelper;

    private final UserRepository userRepository;

    @Override
    public AuthUserRes auth(AuthUserReq dto) {
        String token = this.authHelper.validateToken(dto.getToken());

        if (token == null) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        String subject = this.authHelper.getSubject(token);
        User user = this.userRepository.getByEmail(subject);

        if (user == null) {
            throw new ErrorHandler(ErrorType.USER_NOT_FOUND);
        }

        return AuthMapper.auth().toResponse(user);
    }

    @Override
    public void register(RegisterUserReq dto) {
        var emailCheck = this.userRepository.getByEmail(dto.getEmail());
        if (emailCheck != null) throw new ErrorHandler(ErrorType.EMAIL_ALREADY_EXISTS);

        User user = new User();

        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setPassword(this.authHelper.hashPassword(dto.getPassword()));
        user.setEmail(dto.getEmail());

        user.setMemberSince(LocalDateTime.now());
        user.setLastLogin(LocalDateTime.now());
        user.setRoles(List.of(Role.USER));
        user.setStatus(Status.ACTIVE);

        user.setPortraitImage("");
        user.setProfileImage("");
        user.setShortDescription("");
        user.setLongDescription("");
        user.setInstruments(List.of());
        user.setStyles(List.of());

        this.userRepository.save(user);
    }

    @Override
    public LoginUserRes login(LoginUserReq dto) {
        User user = this.userRepository.getByEmail(dto.getEmail());
        if (user == null) throw new ErrorHandler(ErrorType.USER_NOT_FOUND);

        if (!this.authHelper.validatePassword(user, dto.getPassword())) {
            throw new ErrorHandler(ErrorType.INVALID_PASSWORD);
        }

        user.setLastLogin(LocalDateTime.now());
        this.userRepository.update(user);

        Token token = this.authHelper.createToken(user);

        return AuthMapper.login().toResponse(token);
    }

}
