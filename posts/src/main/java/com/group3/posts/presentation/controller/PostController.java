package com.group3.posts.presentation.controller;

import com.group3.posts.domain.dto.post.mapper.PostMapper;
import com.group3.posts.domain.dto.post.request.*;
import com.group3.posts.presentation.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("api/post")
public class PostController {

    private final PostService postService;

    @GetMapping("get-by-id/{postId}")
    public ResponseEntity<?> getById(
            @PathVariable(value = "postId") String postId
    ) {
        GetPostByIdReq dto = PostMapper.getById().toRequest(postId);

        return ResponseEntity.ok(this.postService.getById(dto));
    }

    @GetMapping("/get-posts")
    public ResponseEntity<?> getPosts(
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "size") Integer size,
            @RequestParam(value = "page") Integer page
    ) {
        GetPostPageReq dto = PostMapper.getPage().toRequest(category, size, page);

        return ResponseEntity.ok(this.postService.getPosts(dto));
    }

    @GetMapping("/get-monthly-posts")
    public ResponseEntity<?> getMonthlyPosts(
            @RequestHeader(value = "Authorization") String token,
            @RequestParam(value = "month") Integer month,
            @RequestParam(value = "year") Integer year
    ) {
        GetMonthlyPostReq dto = PostMapper.getMonthly().toRequest(token, month, year);

        return ResponseEntity.ok(this.postService.getMonthlyPosts(dto));
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody Map<String, Object> payload
    ) {
        CreatePostReq dto = PostMapper.create().toRequest(token, payload);

        return ResponseEntity.ok(this.postService.create(dto));
    }

    @PatchMapping("/edit")
    public ResponseEntity<?> edit(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody Map<String, Object> payload
    ) {
        EditPostReq dto = PostMapper.edit().toRequest(token, payload);

        return ResponseEntity.ok(this.postService.edit(dto));
    }

    @PatchMapping("/toggle-votes")
    public ResponseEntity<?> toggleVotes(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody Map<String, Object> payload
    ) {
        TogglePostVotesReq dto = PostMapper.toggleVotes().toRequest(token, payload);
        this.postService.toggleVotes(dto);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody Map<String, Object> payload
    ) {
        DeletePostReq dto = PostMapper.delete().toRequest(token, payload);
        this.postService.delete(dto);

        return ResponseEntity.ok().build();
    }
}
