package com.group3.user_profiles.presentation.controller;

import com.group3.user_profiles.domain.dto.review.mapper.ReviewMapper;
import com.group3.user_profiles.domain.dto.review.request.CreateReviewReq;
import com.group3.user_profiles.domain.dto.review.request.UpdateReviewReq;
import com.group3.user_profiles.domain.dto.review.request.DeleteReviewReq;
import com.group3.user_profiles.domain.dto.review.request.GetReviewsByAuthorReq;
import com.group3.user_profiles.domain.dto.review.response.CreateReviewRes;
import com.group3.user_profiles.presentation.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/create")
    public ResponseEntity<CreateReviewRes> create(
            @RequestHeader("Authorization") String token,
            @RequestBody Map<String, Object> payload
    ) {
        CreateReviewReq dto = ReviewMapper.create().toRequest(token, payload);
        return ResponseEntity.ok(reviewService.create(dto));
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(
            @RequestHeader("Authorization") String token,
            @RequestBody Map<String, Object> payload
    ) {
        UpdateReviewReq dto = ReviewMapper.update().toRequest(token, payload);
        return ResponseEntity.ok(reviewService.update(dto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(
            @RequestHeader("Authorization") String token,
            @RequestBody Map<String, Object> payload
    ) {
        DeleteReviewReq dto = ReviewMapper.delete().toRequest(token, payload);
        reviewService.delete(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/get-by-author")
    public ResponseEntity<?> getReviewsByAuthor(
            @RequestHeader("Authorization") String token,
            @RequestBody Map<String, Object> payload
    ) {
        GetReviewsByAuthorReq dto = ReviewMapper.getReviewsByAuthor().toRequest(token, payload);
        return ResponseEntity.ok(this.reviewService.getReviewsByAuthor(dto));
    }

}
