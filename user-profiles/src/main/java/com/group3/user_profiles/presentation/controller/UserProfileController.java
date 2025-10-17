package com.group3.user_profiles.presentation.controller;

import com.group3.user_profiles.domain.dto.profile.mapper.UserProfileMapper;
import com.group3.user_profiles.domain.dto.profile.mapper.implementation.ActiveMapper;
import com.group3.user_profiles.domain.dto.profile.request.*;
import com.group3.user_profiles.presentation.service.UserProfileService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user-profiles")
public class UserProfileController {

    private final UserProfileService userService;

    @PostMapping("/create")
    public ResponseEntity<?> create(
            @RequestBody Map<String, Object> payload
    ){
        CreateUserProfileReq dto = UserProfileMapper.create().toRequest(payload);
        this.userService.create(dto);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/get-by-id/{userId}")
    public ResponseEntity<?> getById(
        @RequestHeader(value = "Authorization") String token,
        @PathVariable(value = "userId") String userId
    ) {
        GetUserProfileByIdReq dto = UserProfileMapper.getById().toRequest(userId, token);

        return ResponseEntity.ok(this.userService.getById(dto));
    }

    @PostMapping("/get-user-filtered")
    public ResponseEntity<?> getFiltered(
        @RequestBody Map<String, Object> payload
    ) {
        GetUserProfilePageFilteredReq dto = UserProfileMapper.getFiltered().toRequest(payload);
        return ResponseEntity.ok(this.userService.getProfileFiltered(dto));
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
        this.userService.update(dto);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/active")
    public ResponseEntity<?> active(
        @RequestBody Map<String, Object> payload
    ) {
        ActiveUserProfileReq dto = UserProfileMapper.active().toRequest(payload);
        this.userService.active(dto);

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

    @PostMapping("/get-followers")
    public ResponseEntity<?> getFollowers(
            @RequestBody Map<String, Object> payload
    ) {
        GetFollowerPageReq dto = UserProfileMapper.getFollowerPage().toRequest(payload);

        return ResponseEntity.ok(this.userService.getFollowers(dto));
    }

    @PostMapping("/get-following")
    public ResponseEntity<?> getFollowing(
            @RequestBody Map<String, Object> payload
    ) {
        GetFollowingPageReq dto = UserProfileMapper.getFollowingPage().toRequest(payload);

        return ResponseEntity.ok(this.userService.getFollowing(dto));
    }

    @PostMapping("/get-followers-by-id")
    public ResponseEntity<?> getFollowersById(
            @RequestBody Map<String, Object> payload
    ) {
        GetFollowersByIdReq dto = UserProfileMapper.getFollowersById().toRequest(payload);

        return ResponseEntity.ok(this.userService.getFollowersById(dto));
    }

    @PostMapping("/toggle-follow")
    public ResponseEntity<?> toggleFollow(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody Map<String, Object> payload
    ) {
        ToggleFollowReq dto = UserProfileMapper.toggleFollow().toRequest(token, payload);
        this.userService.toggleFollow(dto);
        return ResponseEntity.ok().build();
    }

}
