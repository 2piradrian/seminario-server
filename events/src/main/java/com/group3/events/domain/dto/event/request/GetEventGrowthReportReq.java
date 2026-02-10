package com.group3.events.domain.dto.event.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class GetEventGrowthReportReq {
    private final String token;
    private final String secret;

    private GetEventGrowthReportReq(String token, String secret) {
        this.token = token;
        this.secret = secret;
    }

    public static GetEventGrowthReportReq create(String token, String secret) {
        if (token == null || token.isBlank()) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }
        if (secret == null || secret.isBlank()) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }
        return new GetEventGrowthReportReq(token, secret);
    }
}