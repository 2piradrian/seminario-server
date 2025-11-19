package com.group3.posts.presentation.controller;

import com.group3.posts.domain.dto.comment.mapper.CommentMapper;
import com.group3.posts.domain.dto.comment.request.CreateCommentReq;
import com.group3.posts.domain.dto.comment.request.DeleteCommentReq;
import com.group3.posts.domain.dto.comment.request.GetCommentByIdReq;
import com.group3.posts.domain.dto.comment.request.GetCommentPageReq;
import com.group3.posts.domain.dto.comment.request.ToggleCommentVotesReq;
import com.group3.posts.presentation.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService service;

    @PostMapping()
    public ResponseEntity<?> create(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody Map<String, Object> payload
    ) {
        CreateCommentReq dto = CommentMapper.create().toRequest(token, payload);

        return ResponseEntity.ok(this.service.create(dto));
    }

    @GetMapping("/get-by-id/{commentId}")
    public ResponseEntity<?> getById(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable(value = "commentId") String commentId
    ) {
        GetCommentByIdReq dto = CommentMapper.getById().toRequest(commentId, token);

        return ResponseEntity.ok(this.service.getById(dto));
    }

    @GetMapping("/get-comments")
    public ResponseEntity<?> getById(
            @RequestHeader(value = "Authorization") String token,
            @RequestParam(value = "postId") String postId,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "size") Integer size
    ) {
        GetCommentPageReq dto = CommentMapper.getPage().toRequest(token, postId, page, size);

        return ResponseEntity.ok(this.service.getComments(dto));
    }

    @PutMapping("/toggle-votes")
    public ResponseEntity<?> toggleVotes(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody Map<String, Object> payload
    ) {
        ToggleCommentVotesReq dto = CommentMapper.toggleVotes().toRequest(token, payload);

        return ResponseEntity.ok(this.service.toggleVotes(dto));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> delete(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable(value = "commentId") String commentId
    ) {
        DeleteCommentReq dto = CommentMapper.delete().toRequest(token, commentId);
        this.service.delete(dto);

        return ResponseEntity.ok().build();
    }

}
