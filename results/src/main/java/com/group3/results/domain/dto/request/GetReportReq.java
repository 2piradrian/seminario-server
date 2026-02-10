package com.group3.results.domain.dto.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class GetReportReq {

    private final String token;

    private GetReportReq(String token) {
        this.token = token;
    }

    public static GetReportReq create(String token) {

        if (token == null || token.isBlank()) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        return new GetReportReq(token);
    }
}
