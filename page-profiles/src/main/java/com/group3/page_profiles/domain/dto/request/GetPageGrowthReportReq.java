package com.group3.page_profiles.domain.dto.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class GetPageGrowthReportReq {
    private final String token;
    private final String secret;

    private GetPageGrowthReportReq(String token, String secret) {
        this.token = token;
        this.secret = secret;
    }

    public static GetPageGrowthReportReq create(String token, String secret) {
        if (token == null || token.isBlank()) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }
        if (secret == null || secret.isBlank()) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }
        return new GetPageGrowthReportReq(token, secret);
    }
}