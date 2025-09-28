package com.group3.posts.domain.dto.post.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class GetMonthlyPostReq {

    private final String token;

    private final Integer month;

    private final Integer year;

    public GetMonthlyPostReq(String token, Integer month, Integer year) {
        this.token = token;
        this.month = month;
        this.year = year;
    }

    public static GetMonthlyPostReq create(String token, Integer month, Integer year) {
        if (token == null || month == null || year == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        if (month < 1 || month > 12) {
            throw new ErrorHandler(ErrorType.INVALID_FIELDS);
        }

        if (year < 0) {
            throw new ErrorHandler(ErrorType.INVALID_FIELDS);
        }

        return new GetMonthlyPostReq(token, month, year);
    }
}
