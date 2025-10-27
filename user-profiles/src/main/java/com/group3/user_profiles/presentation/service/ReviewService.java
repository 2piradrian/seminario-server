package com.group3.user_profiles.presentation.service;

import com.group3.entity.Review;
import com.group3.entity.User;
import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import com.group3.user_profiles.data.repository.ReviewRepository;
import com.group3.user_profiles.data.repository.UserProfileRepository;
import com.group3.user_profiles.data.repository.UserRepository;
import com.group3.user_profiles.domain.dto.review.request.CreateReviewReq;
import com.group3.user_profiles.domain.dto.review.request.DeleteReviewReq;
import com.group3.user_profiles.domain.dto.review.request.UpdateReviewReq;
import com.group3.user_profiles.domain.dto.review.response.CreateReviewRes;
import com.group3.user_profiles.domain.dto.review.response.UpdateReviewRes;
import com.group3.user_profiles.domain.dto.review.mapper.ReviewMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class ReviewService implements ReviewServiceI {

    private final ReviewRepository reviewRepository;

    private final UserRepository userRepository;

    private final UserProfileRepository userProfileRepository;


    // ======== Create Review ========

    @Override
    public CreateReviewRes create(CreateReviewReq dto) {
        User user = this.userRepository.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        Review review = new Review();
        review.setReviewedUser(userProfileRepository.getById(dto.getReviewedUserId()));
        review.setReviewerUser(userProfileRepository.getById(user.getId()));
        review.setReview(dto.getReview());
        review.setRating(dto.getRating());

        Review saved = reviewRepository.save(review);
        return ReviewMapper.create().toResponse(saved);
    }


    // ======== Update Review ========

    @Override
    public UpdateReviewRes update(UpdateReviewReq dto) {
        User user = this.userRepository.auth(dto.getToken());
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
        User user = this.userRepository.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        Review review = reviewRepository.getById(dto.getId());
        if (review == null) throw new ErrorHandler(ErrorType.REVIEW_NOT_FOUND);

        if (!review.getReviewerUser().getId().equals(user.getId()))
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        reviewRepository.delete(dto.getId());
    }

}
