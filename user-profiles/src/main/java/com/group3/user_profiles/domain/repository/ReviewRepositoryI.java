package com.group3.user_profiles.domain.repository;

import com.group3.entity.Review;

public interface ReviewRepositoryI {

    Review save(Review review);

    Review update(Review review);

    void delete(String reviewId);

}
