package com.group3.users.presentation.service;

import com.group3.entity.Token;
import com.group3.entity.User;
import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import com.group3.users.config.helpers.AuthHelper;
import com.group3.users.config.helpers.JWTHelper;
import com.group3.users.data.repository.UserRepository;
import com.group3.users.domain.dto.user.mapper.UserMapper;
import com.group3.users.domain.dto.user.request.AuthUserReq;
import com.group3.users.domain.dto.user.response.AuthUserRes;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@AllArgsConstructor
public class AuthService {

    private final AuthHelper authHelper;

    private final UserRepository userRepository;

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

}
