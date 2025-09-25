package com.group3.profiles.presentation.controller;

import com.group3.profiles.domain.dto.user.mapper.UserMapper;
import com.group3.profiles.domain.dto.user.request.*;
import com.group3.profiles.presentation.service.ProfileService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/profiles")
public class ProfileController {

    private final ProfileService userService;

    @GetMapping("/get-by-id/{userId}")
    public ResponseEntity<?> getById(
        @PathVariable(value = "userId") String userId
    ) {
        GetUserByIdReq dto = UserMapper.getById().toRequest(userId);

        return ResponseEntity.ok(this.userService.getById(dto));
    }

    @GetMapping("/get-own-profile")
    public ResponseEntity<?> getOwnProfile(
        @RequestHeader(value = "Authorization") String token
    ) {
        GetOwnProfileReq dto = UserMapper.getOwnProfile().toRequest(token);

        return ResponseEntity.ok(this.userService.getOwnProfile(dto));
    }

    @PutMapping("/edit")
    public ResponseEntity<?> edit(
        @RequestHeader(value = "Authorization") String token,
        @RequestBody Map<String, Object> payload
    ) {
        EditUserReq dto = UserMapper.update().toRequest(token, payload);

        return ResponseEntity.ok(this.userService.update(dto));
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
