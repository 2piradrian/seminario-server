package com.group3.users.presentation.controller;

import com.group3.users.domain.dto.user.mapper.UserMapper;
import com.group3.users.domain.dto.user.request.*;
import com.group3.users.presentation.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/get-by-id/{userId}")
    public ResponseEntity<?> getById(
        @PathVariable(value = "userId") String userId
    ) {
        GetUserByIdReq dto = UserMapper.getById().toRequest(userId);

        return ResponseEntity.ok(this.userService.getById(dto));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(
        @RequestBody Map<String, Object> payload
    ) {
        RegisterUserReq dto = UserMapper.register().toRequest(payload);

        return ResponseEntity.ok(this.userService.register(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
        @RequestBody Map<String, Object> payload
    ) {
        LoginUserReq dto = UserMapper.login().toRequest(payload);

        return ResponseEntity.ok(this.userService.login(dto));
    }

    @GetMapping("/auth")
    public ResponseEntity<?> auth(
        @RequestHeader(value = "Authorization") String token
    ) {
        AuthUserReq dto = UserMapper.auth().toRequest(token);

        return ResponseEntity.ok(this.userService.auth(dto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(
        @RequestHeader(value = "Authorization") String token
    ) {
        DeleteUserReq dto = UserMapper.delete().toRequest(token);
        this.userService.delete(dto);

        return ResponseEntity.ok().build();
    }

}
