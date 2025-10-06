package com.group3.posts.presentation.controller;

import com.group3.posts.domain.dto.post.mapper.PostMapper;
import com.group3.posts.domain.dto.post.request.*;
import com.group3.posts.presentation.service.PostServiceI;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostServiceI service;

    @GetMapping("/get-by-id/{postId}")
    public ResponseEntity<?> getById(
            @PathVariable(value = "postId") String postId
    ) {
        GetPostByIdReq dto = PostMapper.getById().toRequest(postId);

        return ResponseEntity.ok(this.service.getById(dto));
    }

    @PostMapping("/get-posts")
    public ResponseEntity<?> getPosts(
            @RequestBody Map<String, Object> payload
    ) {
        GetPostPageReq dto = PostMapper.getPage().toRequest(payload);

        return ResponseEntity.ok(this.service.getPosts(dto));
    }

    @PostMapping("/get-by-profile")
    public ResponseEntity<?> getPostsByProfile(
            @RequestBody Map<String, Object> payload
    ) {
        GetPostPageByProfileReq dto = PostMapper.getPageByProfile().toRequest(payload);
        return ResponseEntity.ok(this.service.getPostsByProfile(dto));
    }

    @PostMapping("/get-own-posts")
    public ResponseEntity<?> getOwnPosts(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody Map<String, Object> payload
    ) {
        GetOwnPostPageReq dto = PostMapper.getOwnPage().toRequest(token, payload);
        return ResponseEntity.ok(this.service.getOwnPosts(dto));
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody Map<String, Object> payload
    ) {
        CreatePostReq dto = PostMapper.create().toRequest(token, payload);

        return ResponseEntity.ok(this.service.create(dto));
    }

    @PatchMapping("/edit")
    public ResponseEntity<?> edit(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody Map<String, Object> payload
    ) {
        EditPostReq dto = PostMapper.edit().toRequest(token, payload);

        return ResponseEntity.ok(this.service.edit(dto));
    }

    @PatchMapping("/toggle-votes")
    public ResponseEntity<?> toggleVotes(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody Map<String, Object> payload
    ) {
        TogglePostVotesReq dto = PostMapper.toggleVotes().toRequest(token, payload);

        return ResponseEntity.ok(this.service.toggleVotes(dto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody Map<String, Object> payload
    ) {
        DeletePostReq dto = PostMapper.delete().toRequest(token, payload);
        this.service.delete(dto);

        return ResponseEntity.ok().build();
    }

}
