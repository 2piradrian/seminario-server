package com.group3.users.domain.dto.review.response;

import com.group3.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetPageReviewsByReviewedIdRes {

    private final List<Review> reviews;

    private final Integer nextPage;
}
