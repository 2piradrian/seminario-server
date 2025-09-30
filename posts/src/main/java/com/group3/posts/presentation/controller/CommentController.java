package com.group3.posts.presentation.controller;

import com.group3.posts.domain.dto.comment.mapper.CommentMapper;
import com.group3.posts.domain.dto.comment.request.CreateCommentReq;
import com.group3.posts.domain.dto.comment.request.DeleteCommentReq;
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
    public ResponseEntity<?> getByForumId(
            @RequestParam(value = "forumId") String forumId,
            @RequestParam(value = "size") Integer size,
            @RequestParam(value = "page") Integer page
    ) {
        GetCommentPageReq dto = CommentMapper.getPage().toRequest(forumId, size, page);

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

    @PutMapping("/toggle-votes")
    public ResponseEntity<?> toggleVotes(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody Map<String, Object> payload
    ) {
        ToggleCommentVotesReq dto = CommentMapper.toggleVotes().toRequest(token, payload);
        this.commentService.toggleVotes(dto);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody Map<String, Object> payload
    ) {
        DeleteCommentReq dto = CommentMapper.delete().toRequest(token, payload);
        this.commentService.delete(dto);

        return ResponseEntity.ok().build();
    }

}
