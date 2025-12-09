package com.group3.users.presentation.controller;

import com.group3.users.domain.dto.auth.mapper.AuthMapper;
import com.group3.users.domain.dto.auth.request.*;
import com.group3.users.presentation.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @GetMapping
    public ResponseEntity<?> auth(
            @RequestHeader(value = "Authorization") String token
    ) {
        AuthUserReq dto = AuthMapper.auth().toRequest(token);

        return ResponseEntity.ok(this.authService.auth(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody Map<String, Object> payload
    ) {
        LoginUserReq dto = AuthMapper.login().toRequest(payload);

        return ResponseEntity.ok(this.authService.login(dto));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody Map<String, Object> payload
    ) {
        RegisterUserReq dto = AuthMapper.register().toRequest(payload);
        this.authService.register(dto);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/grant-role")
    public ResponseEntity<?> grantRole(
        @RequestHeader(value = "Authorization") String token,
        @RequestBody Map<String, Object> payload
    ) {
        GrantRoleUserReq dto = AuthMapper.grantRole().toRequest(token, payload);
        this.authService.grantRole(dto);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/revoke-role")
    public ResponseEntity<?> revokeRole(
        @RequestHeader(value = "Authorization") String token,
        @RequestBody Map<String, Object> payload
    ) {
        RevokeRoleUserReq dto = AuthMapper.revokeRole().toRequest(token, payload);
        this.authService.revokeRole(dto);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/verify-email/{token}")
    public ResponseEntity<?> verifyEmail(
            @PathVariable(value = "token") String token
    ) {
        VerifyEmailReq dto = AuthMapper.verifyEmail().toRequest(token);
        this.authService.verifyEmail(dto);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/recover-password")
    public ResponseEntity<?> recoverPassword(
        @RequestBody Map<String, Object> payload
    ) {
        RecoverPasswordReq dto = AuthMapper.recover().toRequest(payload);
        this.authService.recoverPassword(dto);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/resend-verify-email")
    public ResponseEntity<?> resendVerifyEmail(
            @RequestBody Map<String, Object> payload
    ) {
        ResendEmailReq dto = AuthMapper.resendEmail().toRequest(payload);
        this.authService.resendVerifyEmail(dto);

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/change-password")
    public ResponseEntity<?> changePassword(
        @RequestHeader(value = "Authorization") String token,
        @RequestBody Map<String, Object> payload
    ) {
        ChangePasswordReq dto = AuthMapper.changePassword().toRequest(token, payload);
        this.authService.changePassword(dto);

        return ResponseEntity.ok().build();
    }

}
