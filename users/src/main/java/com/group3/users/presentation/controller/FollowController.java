package com.group3.users.presentation.controller;

import com.group3.users.domain.dto.follow.mapper.FollowMapper;
import com.group3.users.domain.dto.follow.request.GetFollowerPageReq;
import com.group3.users.domain.dto.follow.request.GetFollowersByIdReq;
import com.group3.users.domain.dto.follow.request.GetFollowingByIdReq;
import com.group3.users.domain.dto.follow.request.GetFollowingPageReq;
import com.group3.users.domain.dto.follow.request.ToggleFollowReq;
import com.group3.users.presentation.service.FollowService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/follows")
public class FollowController {

    private final FollowService followService;

    @PostMapping("/toggle-follow")
    public ResponseEntity<?> toggleFollow(
            @RequestHeader("Authorization") String token,
            @RequestBody Map<String, Object> payload
    ) {
        ToggleFollowReq dto = FollowMapper.toggleFollow().toRequest(token, payload);
        followService.toggleFollow(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/get-followers")
    public ResponseEntity<?> getFollowers(
            @RequestHeader("Authorization") String token,
            @RequestBody Map<String, Object> payload
    ) {
        GetFollowerPageReq dto = FollowMapper.getFollowerPage().toRequest(token, payload);
        return ResponseEntity.ok(followService.getFollowers(dto));
    }

    @PostMapping("/get-following")
    public ResponseEntity<?> getFollowing(
            @RequestHeader("Authorization") String token,
            @RequestBody Map<String, Object> payload
    ) {
        GetFollowingPageReq dto = FollowMapper.getFollowingPage().toRequest(token, payload);
        return ResponseEntity.ok(followService.getFollowing(dto));
    }

    @PostMapping("/get-followers-by-id")
    public ResponseEntity<?> getFollowersById(
            @RequestBody Map<String, Object> payload
    ) {
        GetFollowersByIdReq dto = FollowMapper.getFollowersById().toRequest(payload);
        return ResponseEntity.ok(followService.getFollowersById(dto));
    }

    @PostMapping("/get-following-by-id")
    public ResponseEntity<?> getFollowingById(
            @RequestBody Map<String, Object> payload
    ) {
        GetFollowingByIdReq dto = FollowMapper.getFollowingById().toRequest(payload);
        return ResponseEntity.ok(followService.getFollowingById(dto));
    }

}
