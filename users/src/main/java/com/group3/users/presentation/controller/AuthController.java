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

    @GetMapping("/")
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

    @GetMapping("/verify-email/{token}")
    public ResponseEntity<?> verifyEmail(
            @PathVariable(value = "token") String token
    ) {
        VerifyEmailReq dto = AuthMapper.verifyEmail().toRequest(token);
        this.authService.verifyEmail(dto);

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

}
