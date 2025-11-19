package com.group3.users.presentation.controller;

import com.group3.users.domain.dto.review.request.*;
import com.group3.users.domain.dto.review.mapper.ReviewMapper;
import com.group3.users.domain.dto.review.response.CreateReviewRes;
import com.group3.users.domain.dto.user.mapper.UserMapper;
import com.group3.users.domain.dto.user.request.GetUserByIdReq;
import com.group3.users.presentation.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/get-by-id/{reviewId}")
    public ResponseEntity<?> getById(
        @RequestHeader(value = "Authorization") String token,
        @PathVariable(value = "reviewId") String reviewId
    ) {
        GetReviewByIdReq dto = ReviewMapper.getById().toRequest(token, reviewId);

        return ResponseEntity.ok(this.reviewService.getById(dto));
    }

    @PostMapping()
    public ResponseEntity<CreateReviewRes> create(
            @RequestHeader("Authorization") String token,
            @RequestBody Map<String, Object> payload
    ) {
        CreateReviewReq dto = ReviewMapper.create().toRequest(token, payload);
        return ResponseEntity.ok(this.reviewService.create(dto));
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<?> update(
            @RequestHeader("Authorization") String token,
            @PathVariable("reviewId") String reviewId,
            @RequestBody Map<String, Object> payload
    ) {
        UpdateReviewReq dto = ReviewMapper.update().toRequest(token, reviewId, payload);
        return ResponseEntity.ok(reviewService.update(dto));
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<?> delete(
            @RequestHeader("Authorization") String token,
            @PathVariable("reviewId") String reviewId
    ) {
        DeleteReviewReq dto = ReviewMapper.delete().toRequest(token, reviewId);
        reviewService.delete(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get-by-author")
    public ResponseEntity<?> getReviewsByAuthor(
            @RequestHeader("Authorization") String token,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "size") Integer size
    ) {
        GetReviewsByAuthorReq dto = ReviewMapper.getReviewsByAuthor().toRequest(token, page, size);
        return ResponseEntity.ok(this.reviewService.getReviewsByAuthor(dto));
    }

    @GetMapping("/get-by-reviewed")
    public ResponseEntity<?> getReviewsByReviewedId(
            @RequestHeader("Authorization") String token,
            @RequestParam("userId") String userId,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "size") Integer size
    ) {
        GetPageReviewsByReviewedIdReq dto = ReviewMapper.getReviewsByReviewed().toRequest(token, userId, page, size);
        return ResponseEntity.ok(this.reviewService.getReviewsByReviewedId(dto));
    }

}
