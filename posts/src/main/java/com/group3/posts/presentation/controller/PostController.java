package com.group3.posts.presentation.controller;

import com.group3.posts.domain.dto.post.mapper.PostMapper;
import com.group3.posts.domain.dto.post.request.*;
import com.group3.posts.presentation.service.PostService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostService service;

    @PostMapping
    public ResponseEntity<?> create(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody Map<String, Object> payload
    ) {
        CreatePostReq dto = PostMapper.create().toRequest(token, payload);

        return ResponseEntity.ok(this.service.create(dto));
    }

    @GetMapping("/get-by-id/{postId}")
    public ResponseEntity<?> getById(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable(value = "postId") String postId
    ) {
        GetPostByIdReq dto = PostMapper.getById().toRequest(postId, token);

        return ResponseEntity.ok(this.service.getById(dto));
    }

    @GetMapping("/get-posts")
    public ResponseEntity<?> getPosts(
            @RequestHeader(value = "Authorization") String token,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "size") Integer size
    ) {
        GetPostPageReq dto = PostMapper.getPage().toRequest(page, size, token);

        return ResponseEntity.ok(this.service.getPosts(dto));
    }

    @GetMapping("/get-page-posts")
    public ResponseEntity<?> getPagePosts(
        @RequestHeader(value = "Authorization") String token,
        @RequestParam(value = "secret") String secret,
        @RequestParam(value = "page") Integer page,
        @RequestParam(value = "size") Integer size
    ) {
        GetOnlyPagePostPageReq dto = PostMapper.getOnlyPage().toRequest(page, size, token, secret);

        return ResponseEntity.ok(this.service.getPageOnlyPosts(dto));
    }

    @GetMapping("/get-filtered-posts")
    public ResponseEntity<?> getFilteredPosts(
            @RequestHeader(value = "Authorization") String token,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "size") Integer size,
            @RequestParam(value = "text", required = false) String text,
            @RequestParam(value = "postTypeId", required = false) String postTypeId,
            @RequestParam(value = "secret") String secret
    ) {
        GetFilteredPostPageReq dto = PostMapper.getFilteredPage().toRequest(token, page, size, text, postTypeId, secret);

        return ResponseEntity.ok(this.service.getFilteredPosts(dto));
    }

    @GetMapping("/get-by-profile")
    public ResponseEntity<?> getPostsByProfile(
            @RequestHeader(value = "Authorization") String token,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "size") Integer size,
            @RequestParam(value = "profileId") String profileId
    ) {
        GetPostPageByProfileReq dto = PostMapper.getPageByProfile().toRequest(token, page, size, profileId);
        return ResponseEntity.ok(this.service.getPostsByProfile(dto));
    }

    @GetMapping("/get-own-posts")
    public ResponseEntity<?> getOwnPosts(
            @RequestHeader(value = "Authorization") String token,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "size") Integer size
    ) {
        GetOwnPostPageReq dto = PostMapper.getOwnPage().toRequest(token, page, size);
        return ResponseEntity.ok(this.service.getOwnPosts(dto));
    }

    @PatchMapping("/toggle-votes")
    public ResponseEntity<?> toggleVotes(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody Map<String, Object> payload
    ) {
        TogglePostVotesReq dto = PostMapper.toggleVotes().toRequest(token, payload);

        return ResponseEntity.ok(this.service.toggleVotes(dto));
    }

    @PutMapping("/{postId}")
    public ResponseEntity<?> edit(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable(value = "postId") String postId,
            @RequestBody Map<String, Object> payload
    ) {
        EditPostReq dto = PostMapper.edit().toRequest(token, postId, payload);

        return ResponseEntity.ok(this.service.edit(dto));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<?> delete(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable(value = "postId") String postId
    ) {
        DeletePostReq dto = PostMapper.delete().toRequest(token, postId);
        this.service.delete(dto);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete-by/user/{userId}")
    public ResponseEntity<?> deleteFromUser(
            @PathVariable(value = "userId") String userId,
            @RequestParam(value = "secret") String secret
    ) {
        DeletePostsByUserIdReq dto = PostMapper.deletePostsByUserId().toRequest(userId, secret);
        this.service.deletePostsByUserId(dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete-by/page/{pageId}")
    public ResponseEntity<?> deleteFromPage(
            @PathVariable(value = "pageId") String pageId,
            @RequestParam(value = "secret") String secret
    ) {
        DeletePostsByPageIdReq dto = PostMapper.deletePostsByPageId().toRequest(pageId, secret);
        this.service.deletePostsByPageId(dto);
        return ResponseEntity.ok().build();
    }
}
