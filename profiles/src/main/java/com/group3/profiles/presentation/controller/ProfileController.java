package com.group3.profiles.presentation.controller;

import com.group3.profiles.domain.dto.profile.mapper.UserProfileMapper;
import com.group3.profiles.domain.dto.profile.request.*;
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
        GetUserProfileByIdReq dto = UserProfileMapper.getById().toRequest(userId);

        return ResponseEntity.ok(this.userService.getById(dto));
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(
        @RequestBody Map<String, Object> payload
    ){
        CreateUserProfileReq dto = UserProfileMapper.create().toRequest(payload);
        this.userService.create(dto);

        return ResponseEntity.ok().build();
    }


    @GetMapping("/get-own-profile")
    public ResponseEntity<?> getOwnProfile(
        @RequestHeader(value = "Authorization") String token
    ) {
        GetOwnUserProfileReq dto = UserProfileMapper.getOwnProfile().toRequest(token);

        return ResponseEntity.ok(this.userService.getOwnProfile(dto));
    }

    @PutMapping("/edit")
    public ResponseEntity<?> edit(
        @RequestHeader(value = "Authorization") String token,
        @RequestBody Map<String, Object> payload
    ) {
        EditUserProfileReq dto = UserProfileMapper.update().toRequest(token, payload);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(
        @RequestHeader(value = "Authorization") String token
    ) {
        DeleteUserProfileReq dto = UserProfileMapper.delete().toRequest(token);
        this.userService.delete(dto);

        return ResponseEntity.ok().build();
    }

}
