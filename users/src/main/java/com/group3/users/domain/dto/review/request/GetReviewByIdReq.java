package com.group3.users.domain.dto.review.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class GetReviewByIdReq {

    private final String reviewId;

    private final String token;

    private GetReviewByIdReq(String token, String reviewId){
        this.reviewId = reviewId;
        this.token = token;
    }

    public static GetReviewByIdReq create(String token, String reviewId){

        if (token == null || token.isEmpty()){
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        if (reviewId == null){
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        return new GetReviewByIdReq(token, reviewId);
    }

}
