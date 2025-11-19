package com.group3.users.presentation.controller;

import com.group3.users.domain.dto.follow.mapper.FollowMapper;
import com.group3.users.domain.dto.follow.request.*;
import com.group3.users.presentation.service.FollowService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

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

    @GetMapping("/get-followers")
    public ResponseEntity<?> getFollowers(
            @RequestHeader("Authorization") String token,
            @RequestParam(value = "userId") String userId,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "size") Integer size
    ) {
        GetFollowerPageReq dto = FollowMapper.getFollowerPage().toRequest(token, userId, page, size);
        return ResponseEntity.ok(followService.getFollowers(dto));
    }

    @GetMapping("/get-following")
    public ResponseEntity<?> getFollowing(
            @RequestHeader("Authorization") String token,
            @RequestParam(value = "userId") String userId,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "size") Integer size
    ) {
        GetFollowingPageReq dto = FollowMapper.getFollowingPage().toRequest(token, userId, page, size);
        return ResponseEntity.ok(followService.getFollowing(dto));
    }

    @GetMapping("/get-followers-by-id/{id}")
    public ResponseEntity<?> getFollowersQuantityById(
            @PathVariable(value = "id") String id,
            @RequestParam(value = "secret") String secret
    ) {
        GetFollowersQuantityByIdReq dto = FollowMapper.getFollowersQuantityById().toRequest(id, secret);
        return ResponseEntity.ok(followService.getFollowersQuantityById(dto));
    }

    @GetMapping("/get-following-by-id/{id}")
    public ResponseEntity<?> getFollowingQuantityById(
            @PathVariable(value = "id") String id,
            @RequestParam(value = "secret") String secret
    ) {
        GetFollowingQuantityByIdReq dto = FollowMapper.getFollowingQuantityById().toRequest(id, secret);
        return ResponseEntity.ok(followService.getFollowingQuantityById(dto));
    }

    @GetMapping("/get-all-followers/{id}")
    public ResponseEntity<?> getAllFollowers(
            @PathVariable(value = "id") String id,
            @RequestParam(value = "secret") String secret
    ) {
        GetAllFollowersReq dto = FollowMapper.getAllFollowers().toRequest(id, secret);
        return ResponseEntity.ok(followService.getAllFollowers(dto));
    }

    @GetMapping("/get-all-following/{id}")
    public ResponseEntity<?> getAllFollowing(
            @PathVariable(value = "id") String id,
            @RequestParam(value = "secret") String secret
    ) {
        GetAllFollowingReq dto = FollowMapper.getAllFollowing().toRequest(id, secret);
        return ResponseEntity.ok(followService.getAllFollowing(dto));
    }

}
