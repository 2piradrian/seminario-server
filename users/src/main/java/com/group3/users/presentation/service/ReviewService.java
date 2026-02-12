package com.group3.users.presentation.service;

import com.group3.config.PrefixedUUID;
import com.group3.entity.*;
import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import com.group3.users.config.helpers.SecretKeyHelper;
import com.group3.users.data.repository.NotificationsRepository;
import com.group3.users.data.repository.ReviewRepository;
import com.group3.users.data.repository.UserProfileRepository;
import com.group3.users.domain.dto.review.mapper.ReviewMapper;
import com.group3.users.domain.dto.review.request.*;
import com.group3.users.domain.dto.review.response.*;
import com.group3.users.domain.dto.user.mapper.UserMapper;
import com.group3.users.domain.dto.user.response.GetUserByIdRes;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class ReviewService implements ReviewServiceI {

    private final AuthService authService;

    private final ReviewRepository reviewRepository;

    private final UserProfileRepository userProfileRepository;

    private final NotificationsRepository notificationsRepository;

    private final SecretKeyHelper secretKeyHelper;

    @Override
    public GetUserByIdRes getById(GetReviewByIdReq dto) {
        User user = this.authService.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        Review reviewRepository = this.reviewRepository.getById(dto.getReviewId());
        if (reviewRepository == null) throw new ErrorHandler(ErrorType.REVIEW_NOT_FOUND);

        UserProfile reviewerUser = this.userProfileRepository.getById(reviewRepository.getReviewerUser().getId());
        if (reviewerUser == null) throw new ErrorHandler(ErrorType.USER_NOT_FOUND);

        reviewRepository.setReviewerUser(reviewerUser);

        return UserMapper.getById().toResponse(user);
    }

    // ======== Create Review ========

    @Override
    public CreateReviewRes create(CreateReviewReq dto) {
        User user = this.authService.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        Review review = new Review();

        if(dto.getReviewedUserId().equals(user.getId())) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        UserProfile reviewedUser = this.userProfileRepository.getById(dto.getReviewedUserId());
        if (reviewedUser == null) throw new ErrorHandler(ErrorType.USER_NOT_FOUND);

        review.setId(PrefixedUUID.generate(PrefixedUUID.EntityType.REVIEW).toString());
        review.setReviewedId(dto.getReviewedUserId());
        review.setReviewerUser(userProfileRepository.getById(user.getId()));
        review.setReview(dto.getReview());
        review.setRating(dto.getRating());
        review.setCreatedAt(LocalDateTime.now());
        review.setUpdatedAt(LocalDateTime.now());
        review.setStatus(Status.ACTIVE);

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

        review.setUpdatedAt(LocalDateTime.now());

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

        if (!user.canDelete(review)) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        if (user.isStaff() && !review.getReviewerUser().getId().equals(user.getId())){
            this.notificationsRepository.create(
                    this.secretKeyHelper.getSecret(),
                    review.getReviewerUser().getId(),
                    review.getId(),
                    user.getId(),
                    NotificationContent.MODERATION.name(),
                    dto.getReasonId()

            );
        }

        this.reviewRepository.delete(review.getId());
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

    @Override
    public GetPageReviewsByReviewedIdRes getReviewsByReviewedId(GetPageReviewsByReviewedIdReq dto) {
        User user = this.authService.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        PageContent<Review> reviews = this.reviewRepository.findByReviewedUserId(dto.getUserId(), dto.getPage(), dto.getSize());

        for (Review review : reviews.getContent()) {
            UserProfile reviewerUser = this.userProfileRepository.getById(review.getReviewerUser().getId());
            review.setReviewerUser(reviewerUser);
        }

        return ReviewMapper.getReviewsByReviewed().toResponse(reviews);
    }

}
