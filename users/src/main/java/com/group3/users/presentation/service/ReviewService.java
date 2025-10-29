package com.group3.users.presentation.service;

import com.group3.entity.PageContent;
import com.group3.entity.Review;
import com.group3.entity.User;
import com.group3.entity.UserProfile;
import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import com.group3.users.data.repository.ReviewRepository;
import com.group3.users.data.repository.UserProfileRepository;
import com.group3.users.data.repository.UserRepository;
import com.group3.users.domain.dto.review.mapper.ReviewMapper;
import com.group3.users.domain.dto.review.request.*;
import com.group3.users.domain.dto.review.response.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class ReviewService implements ReviewServiceI {

    private final AuthService authService;

    private final ReviewRepository reviewRepository;

    private final UserProfileRepository userProfileRepository;


    // ======== Create Review ========

    @Override
    public CreateReviewRes create(CreateReviewReq dto) {
        User user = this.authService.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        Review review = new Review();
        review.setReviewedId(dto.getReviewedUserId());
        review.setReviewerUser(userProfileRepository.getById(user.getId()));
        review.setReview(dto.getReview());
        review.setRating(dto.getRating());

        Review saved = reviewRepository.save(review);
        return ReviewMapper.create().toResponse(saved);
    }


    // ======== Update Review ========

    @Override
    public UpdateReviewRes update(UpdateReviewReq dto) {
        User user = this.authService.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        Review review = reviewRepository.getById(dto.getId());
        if (review == null) throw new ErrorHandler(ErrorType.REVIEW_NOT_FOUND);

        if (!review.getReviewerUser().getId().equals(user.getId()))
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        if (dto.getReview() != null) review.setReview(dto.getReview());
        if (dto.getRating() != null) review.setRating(dto.getRating());

        Review updated = reviewRepository.update(review);
        return ReviewMapper.update().toResponse(updated);
    }


    // ======== Delete Review ========

    @Override
    public void delete(DeleteReviewReq dto) {
        User user = this.authService.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        Review review = reviewRepository.getById(dto.getId());
        if (review == null) throw new ErrorHandler(ErrorType.REVIEW_NOT_FOUND);

        if (!review.getReviewerUser().getId().equals(user.getId()))
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        reviewRepository.delete(dto.getId());
    }

    @Override
    public GetReviewsByAuthorRes getReviewsByAuthor(GetReviewsByAuthorReq dto) {
        User user = this.authService.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        PageContent<Review> reviews = this.reviewRepository.findByReviewerId(user.getId(), dto.getPage(), dto.getSize());

        for (Review review : reviews.getContent()) {
            UserProfile reviewedUser = this.userProfileRepository.getById(review.getReviewerUser().getId());
            review.setReviewerUser(reviewedUser);
        }

        return ReviewMapper.getReviewsByAuthor().toResponse(reviews);
    }

}
