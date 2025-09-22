package com.group3.users.presentation.controller;

import com.group3.users.domain.dto.user.mapper.UserMapper;
import com.group3.users.domain.dto.user.request.AuthUserReq;
import com.group3.users.domain.dto.user.request.LoginUserReq;
import com.group3.users.domain.dto.user.request.RegisterUserReq;
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

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody Map<String, Object> payload
    ) {
        RegisterUserReq dto = UserMapper.register().toRequest(payload);
        this.authService.register(dto);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody Map<String, Object> payload
    ) {
        LoginUserReq dto = UserMapper.login().toRequest(payload);

        return ResponseEntity.ok(this.authService.login(dto));
    }

    @GetMapping("/auth")
    public ResponseEntity<?> auth(
            @RequestHeader(value = "Authorization") String token
    ) {
        AuthUserReq dto = UserMapper.auth().toRequest(token);

        return ResponseEntity.ok(this.authService.auth(dto));
    }

}
