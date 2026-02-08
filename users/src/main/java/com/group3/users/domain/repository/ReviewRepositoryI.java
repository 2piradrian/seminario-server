package com.group3.users.domain.repository;

import com.group3.entity.PageContent;
import com.group3.entity.Review;

public interface ReviewRepositoryI {

    Review getById(String reviewId);

    Review save(Review review);

    Review update(Review review);

    void delete(String reviewId);

    PageContent<Review> findByReviewerId(String reviewerId, Integer page, Integer size);

    PageContent<Review> findByReviewedUserId(String reviewedUserId, Integer page, Integer size);

    void deleteByReviewerId(String reviewerId);

    void deleteByReviewedId(String reviewedId);

}
