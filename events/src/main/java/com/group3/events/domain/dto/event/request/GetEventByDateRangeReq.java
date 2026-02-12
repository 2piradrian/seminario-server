package com.group3.events.domain.dto.event.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

import java.util.Date;

@Getter
public class GetEventByDateRangeReq {

    private final String token;

    private final String profileId;

    private final Date dateMonth;

    private GetEventByDateRangeReq(String token, String profileId, Date dateMonth) {
        this.dateMonth = dateMonth;
        this.token = token;
        this.profileId = profileId;
    }

    public static GetEventByDateRangeReq create(String token, String userId, Date dateMonth) {
        if (token == null) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        if (userId == null || dateMonth == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }


        return new GetEventByDateRangeReq(token, userId, dateMonth);
    }
}
