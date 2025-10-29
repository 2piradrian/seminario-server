package com.group3.user_profiles.domain.repository;

import com.group3.entity.PageContent;
import com.group3.entity.Review;

public interface ReviewRepositoryI {

    Review getById(String reviewId);

    Review save(Review review);

    Review update(Review review);

    void delete(String reviewId);

    PageContent<Review> findByReviewerId(String reviewerId, Integer page, Integer size);

}
