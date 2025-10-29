package com.group3.users.presentation.service;

import com.group3.config.PrefixedUUID;
import com.group3.entity.*;
import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import com.group3.users.config.helpers.AuthHelper;
import com.group3.users.config.helpers.EmailHelper;
import com.group3.users.config.helpers.SecretKeyHelper;
import com.group3.users.data.repository.UserProfileRepository;
import com.group3.users.data.repository.UserRepository;
import com.group3.users.domain.dto.auth.mapper.AuthMapper;
import com.group3.users.domain.dto.auth.request.*;
import com.group3.users.domain.dto.auth.response.AuthUserRes;
import com.group3.users.domain.dto.auth.response.LoginUserRes;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class AuthService implements AuthServiceI {

    private final AuthHelper authHelper;

    private final UserRepository userRepository;

    private final EmailService emailService;

    private final EmailHelper emailHelper;

    // ======== Authenticate User ========

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

    public User auth(String reqToken) {
        String token = this.authHelper.validateToken(reqToken);

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

        return user;
    }

    // ======== Register User ========

    @Override
    public void register(RegisterUserReq dto) {
        var emailCheck = this.userRepository.getByEmail(dto.getEmail());
        if (emailCheck != null) throw new ErrorHandler(ErrorType.EMAIL_ALREADY_EXISTS);

        String userId = PrefixedUUID.generate(PrefixedUUID.EntityType.USER).toString();

        UserProfile userProfile = UserProfile.builder()
                .id(userId)
                .name(dto.getName())
                .surname(dto.getSurname())
                .memberSince(LocalDateTime.now())
                .portraitImage("")
                .profileImage("")
                .shortDescription("¡New user!")
                .longDescription("¡New user!")
                .instruments(List.of())
                .styles(List.of())
                .build();

        User user = User.builder()
                .id(userId)
                .password(this.authHelper.hashPassword(dto.getPassword()))
                .email(dto.getEmail())
                .role(Role.USER)
                .status(Status.INACTIVE)
                .profile(userProfile)
                .build();

        User saved = this.userRepository.save(user);

        Token token = this.authHelper.createToken(saved);

        this.emailService.sendEmail(saved.getEmail(),"Email Validation", this.emailHelper.verifyEmailHTML(token.getAccessToken()));
    }

    // ======== Grant roles to user ========

    @Override
    public void grantRole(GrantRoleUserReq dto){
        AuthUserRes adminAuth = this.auth(AuthUserReq.create(dto.getToken()));
        if (adminAuth == null) throw new ErrorHandler(ErrorType.USER_NOT_FOUND);

        if (!adminAuth.getRole().canAsignRole()) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        User userUpdated = this.userRepository.getByEmail(dto.getEmail());
        if (userUpdated == null) throw new ErrorHandler(ErrorType.USER_NOT_FOUND);

        Role newRole = Role.fromString(dto.getRoleId());
        if (newRole == null) throw new ErrorHandler(ErrorType.ROLE_NOT_FOUND);

        if(userUpdated.getRole().equals(newRole)) throw new ErrorHandler(ErrorType.USER_ALREADY_HAS_ROLE);

        userUpdated.setRole(newRole);
        this.userRepository.update(userUpdated);
    }

    // ======== Revoke roles to user ========

    @Override
    public void revokeRole(RevokeRoleUserReq dto){
        AuthUserRes adminAuth = this.auth(AuthUserReq.create(dto.getToken()));
        if (adminAuth == null) throw new ErrorHandler(ErrorType.USER_NOT_FOUND);

        if (!adminAuth.getRole().canAsignRole()) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        User userUpdated = this.userRepository.getByEmail(dto.getEmail());
        if (userUpdated == null) throw new ErrorHandler(ErrorType.USER_NOT_FOUND);

        if(!userUpdated.getRole().canDelete()) throw new ErrorHandler(ErrorType.USER_ALREADY_HAS_NO_ROLE);

        userUpdated.setRole(Role.USER);
        this.userRepository.update(userUpdated);
    }

    // ======== Login User ========

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

    // ======== Verify Email ========

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

    // ======== Resend Verify Email ========

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
