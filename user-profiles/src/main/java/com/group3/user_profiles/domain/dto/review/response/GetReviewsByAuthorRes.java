package com.group3.user_profiles.domain.dto.review.response;

import com.group3.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetReviewsByAuthorRes {

    private final List<Review> reviews;

    private final Integer nextPage;

}
