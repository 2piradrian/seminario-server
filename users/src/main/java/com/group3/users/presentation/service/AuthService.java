package com.group3.users.presentation.service;

import com.group3.entity.Role;
import com.group3.entity.Status;
import com.group3.entity.Token;
import com.group3.entity.User;
import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import com.group3.users.config.helpers.AuthHelper;
import com.group3.users.data.repository.UserRepository;
import com.group3.users.domain.dto.user.mapper.UserMapper;
import com.group3.users.domain.dto.user.request.AuthUserReq;
import com.group3.users.domain.dto.user.request.LoginUserReq;
import com.group3.users.domain.dto.user.request.RegisterUserReq;
import com.group3.users.domain.dto.user.response.AuthUserRes;
import com.group3.users.domain.dto.user.response.LoginUserRes;
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

        return UserMapper.auth().toResponse(user);
    }

    @Override
    public void register(RegisterUserReq dto) {
        var emailCheck = this.userRepository.getByEmail(dto.getEmail());
        if (emailCheck != null) throw new ErrorHandler(ErrorType.EMAIL_ALREADY_EXISTS);

        var usernameCheck = this.userRepository.getByFullName(dto.getName(), dto.getSurname());
        if (!usernameCheck.isEmpty()) throw new ErrorHandler(ErrorType.FULLNAME_ALREADY_EXISTS);

        User user = new User();

        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setEmail(dto.getEmail());
        user.setPassword(this.authHelper.hashPassword(dto.getPassword()));
        user.setStatus(Status.ACTIVE);
        user.setRoles(List.of(Role.USER));
        user.setMemberSince(LocalDateTime.now());
        user.setLastLogin(LocalDateTime.now());

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

        return UserMapper.login().toResponse(token);
    }

}
