package com.group3.posts.presentation.controller;

import com.group3.posts.domain.dto.comment.mapper.CommentMapper;
import com.group3.posts.domain.dto.comment.request.CreateCommentReq;
import com.group3.posts.domain.dto.comment.request.EditCommentReq;
import com.group3.posts.domain.dto.comment.request.GetCommentPageReq;
import com.group3.posts.domain.dto.comment.request.ToggleCommentVotesReq;
import com.group3.posts.presentation.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/get-comments")
    public ResponseEntity<?> getByPostId(
            @RequestParam(value = "postId") String postId,
            @RequestParam(value = "size") Integer size,
            @RequestParam(value = "page") Integer page
    ) {
        GetCommentPageReq dto = CommentMapper.getPage().toRequest(postId, size, page);

        return ResponseEntity.ok(this.commentService.getComments(dto));
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody Map<String, Object> payload
    ) {
        CreateCommentReq dto = CommentMapper.create().toRequest(token, payload);

        return ResponseEntity.ok(this.commentService.create(dto));
    }

    @PatchMapping("/edit")
    public ResponseEntity<?> edit(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody Map<String, Object> payload
    ) {
        EditCommentReq dto = CommentMapper.edit().toRequest(token, payload);

        return ResponseEntity.ok(this.commentService.edit(dto));
    }

    @PatchMapping("/toggle-votes")
    public ResponseEntity<?> toggleVotes(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody Map<String, Object> payload
    ) {
        ToggleCommentVotesReq dto = CommentMapper.toggleVotes().toRequest(token, payload);
        this.commentService.toggleVotes(dto);

        return ResponseEntity.ok().build();
    }
}
