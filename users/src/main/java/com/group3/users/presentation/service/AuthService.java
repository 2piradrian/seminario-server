package com.group3.users.presentation.service;

import com.group3.entity.Role;
import com.group3.entity.Status;
import com.group3.entity.Token;
import com.group3.entity.User;
import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import com.group3.users.config.helpers.AuthHelper;
import com.group3.users.config.helpers.EmailHelper;
import com.group3.users.config.helpers.SecretKeyHelper;
import com.group3.users.data.repository.ProfileRepository;
import com.group3.users.data.repository.UserRepository;
import com.group3.users.domain.dto.auth.mapper.AuthMapper;
import com.group3.users.domain.dto.auth.request.*;
import com.group3.users.domain.dto.auth.response.AuthUserRes;
import com.group3.users.domain.dto.auth.response.LoginUserRes;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class AuthService implements AuthServiceI {

    private final AuthHelper authHelper;

    private final SecretKeyHelper secretKeyHelper;

    private final UserRepository userRepository;

    private final ProfileRepository profileRepository;

    private final EmailService emailService;

    private final EmailHelper emailHelper;

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

        if (user.getStatus() == Status.INACTIVE) {
            throw new ErrorHandler(ErrorType.USER_NOT_ACTIVATED);
        }

        if (user.getStatus() == Status.DELETED){
            throw new ErrorHandler(ErrorType.USER_DELETED);
        }

        return AuthMapper.auth().toResponse(user);
    }

    @Override
    public void register(RegisterUserReq dto) {
        var emailCheck = this.userRepository.getByEmail(dto.getEmail());
        if (emailCheck != null) throw new ErrorHandler(ErrorType.EMAIL_ALREADY_EXISTS);

        User user = new User();

        user.setPassword(this.authHelper.hashPassword(dto.getPassword()));
        user.setEmail(dto.getEmail());
        user.setRoles(List.of(Role.USER));
        user.setStatus(Status.INACTIVE);

        User saved = this.userRepository.save(user);
        this.profileRepository.create(saved.getId(), dto.getEmail(), dto.getName(), dto.getSurname(), secretKeyHelper.getSecret());

        Token token = this.authHelper.createToken(saved);

        this.emailService.sendEmail(saved.getEmail(),"Email Validation", this.emailHelper.verifyEmailHTML(token.getAccessToken()));
    }

    @Override
    public LoginUserRes login(LoginUserReq dto) {
        User user = this.userRepository.getByEmail(dto.getEmail());
        if (user == null) throw new ErrorHandler(ErrorType.USER_NOT_FOUND);

        if (user.getStatus() == Status.INACTIVE){
            throw new ErrorHandler(ErrorType.USER_NOT_ACTIVATED);
        }

        if (user.getStatus() == Status.DELETED){
            throw new ErrorHandler(ErrorType.USER_DELETED);
        }

        if (!this.authHelper.validatePassword(user, dto.getPassword())) {
            throw new ErrorHandler(ErrorType.INVALID_PASSWORD);
        }

        this.userRepository.update(user);
        Token token = this.authHelper.createToken(user);

        return AuthMapper.login().toResponse(token);
    }

    @Override
    public void verifyEmail(VerifyEmailReq dto){

        String token = this.authHelper.validateUrlToken(dto.getToken());

        if (token == null) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        String subject = this.authHelper.getSubject(token);
        User user = this.userRepository.getByEmail(subject);

        if (user == null) {
            throw new ErrorHandler(ErrorType.USER_NOT_FOUND);
        }

        if (user.getStatus() == Status.ACTIVE) {
            throw new ErrorHandler(ErrorType.USER_ALREADY_ACTIVATED);
        }

        user.setStatus(Status.ACTIVE);

        this.userRepository.update(user);
    }

    @Override
    public void resendVerifyEmail (ResendEmailReq dto){

        User user = this.userRepository.getByEmail(dto.getEmail());

        if (user == null){
            throw new ErrorHandler(ErrorType.USER_NOT_FOUND);
        }

        if (user.getStatus() == Status.ACTIVE) {
            throw new ErrorHandler(ErrorType.USER_ALREADY_ACTIVATED);
        }

        Token token = this.authHelper.createToken(user);

        this.emailService.sendEmail(
            user.getEmail(),
            "Email Verification",
            this.emailHelper.verifyEmailHTML(token.getAccessToken())
        );
    }

}
