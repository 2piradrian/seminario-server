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
@RequestMapping("api/post")
public class PostController {

    private final PostServiceI postServiceI;

    @GetMapping("get-by-id/{postId}")
    public ResponseEntity<?> getById(
            @PathVariable(value = "postId") String postId
    ) {
        GetPostByIdReq dto = PostMapper.getById().toRequest(postId);

        return ResponseEntity.ok(this.postServiceI.getById(dto));
    }

    @PostMapping("/get-posts")
    public ResponseEntity<?> getPosts(
            @RequestBody Map<String, Object> payload
    ) {
        GetPostPageReq dto = PostMapper.getPage().toRequest(payload);

        return ResponseEntity.ok(this.postServiceI.getPosts(dto));
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody Map<String, Object> payload
    ) {
        CreatePostReq dto = PostMapper.create().toRequest(token, payload);

        return ResponseEntity.ok(this.postServiceI.create(dto));
    }

    @PatchMapping("/edit")
    public ResponseEntity<?> edit(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody Map<String, Object> payload
    ) {
        EditPostReq dto = PostMapper.edit().toRequest(token, payload);

        return ResponseEntity.ok(this.postServiceI.edit(dto));
    }

    @PatchMapping("/toggle-votes")
    public ResponseEntity<?> toggleVotes(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody Map<String, Object> payload
    ) {
        TogglePostVotesReq dto = PostMapper.toggleVotes().toRequest(token, payload);
        this.postServiceI.toggleVotes(dto);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody Map<String, Object> payload
    ) {
        DeletePostReq dto = PostMapper.delete().toRequest(token, payload);
        this.postServiceI.delete(dto);

        return ResponseEntity.ok().build();
    }
}
