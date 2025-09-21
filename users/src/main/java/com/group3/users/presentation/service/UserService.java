package com.group3.users.presentation.service;

import com.group3.entity.Role;
import com.group3.entity.Status;
import com.group3.entity.Token;
import com.group3.entity.User;
import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import com.group3.users.data.repository.UserRepository;
import com.group3.users.domain.dto.user.mapper.UserMapper;
import com.group3.users.domain.dto.user.request.*;
import com.group3.users.domain.dto.user.response.AuthUserRes;
import com.group3.users.domain.dto.user.response.GetUserByIdRes;
import com.group3.users.domain.dto.user.response.LoginUserRes;
import com.group3.users.domain.dto.user.response.RegisterUserRes;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class UserService implements UserServiceI {

    private final UserRepository userRepository;

    private final AuthService authService;

    @Override
    public GetUserByIdRes getById(GetUserByIdReq dto) {
        User user = this.userRepository.getById(dto.getUserId());
        if (user == null) throw new ErrorHandler(ErrorType.USER_NOT_FOUND);

        return UserMapper.getById().toResponse(user);
    }

    @Override
    public RegisterUserRes register(RegisterUserReq dto) {
        var emailCheck = this.userRepository.getByEmail(dto.getEmail());
        if (emailCheck != null) throw new ErrorHandler(ErrorType.EMAIL_ALREADY_EXISTS);

        var usernameCheck = this.userRepository.getByFullname(dto.getName(), dto.getSurname());
        if (!usernameCheck.isEmpty()) throw new ErrorHandler(ErrorType.FULLNAME_ALREADY_EXISTS);

        User user = new User();

        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setEmail(dto.getEmail());
        user.setPassword(this.authService.hashPassword(dto.getPassword()));
        user.setStatus(Status.ACTIVE);
        user.setRoles(Set.of(Role.USER));
        user.setMemberSince(LocalDateTime.now());
        user.setLastLogin(LocalDateTime.now());

        User saved = this.userRepository.save(user);

        return UserMapper.register().toResponse(saved);
    }

    @Override
    public LoginUserRes login(LoginUserReq dto) {
        User user = this.userRepository.getByEmail(dto.getEmail());
        if (user == null) throw new ErrorHandler(ErrorType.USER_NOT_FOUND);

        if (!this.authService.validatePassword(user, dto.getPassword())) {
            throw new ErrorHandler(ErrorType.INVALID_PASSWORD);
        }

        user.setLastLogin(LocalDateTime.now());
        this.userRepository.update(user);

        Token token = this.authService.createToken(user);

        return UserMapper.login().toResponse(token);
    }

    @Override
    public AuthUserRes auth(AuthUserReq dto) {
        String token = this.authService.validateToken(dto.getToken());

        if (token == null) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        String subject = this.authService.getSubject(token);
        User user = this.userRepository.getByEmail(subject);

        if (user == null) {
            throw new ErrorHandler(ErrorType.USER_NOT_FOUND);
        }

        return UserMapper.auth().toResponse(user);
    }

    @Override
    public void delete(DeleteUserReq dto) {
        String token = this.authService.validateToken(dto.getToken());

        if (token == null) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        String subject = this.authService.getSubject(token);
        User user = this.userRepository.getByEmail(subject);

        if (user == null) {
            throw new ErrorHandler(ErrorType.USER_NOT_FOUND);
        }

        user.setStatus(Status.DELETED);

        this.userRepository.update(user);
    }

}
