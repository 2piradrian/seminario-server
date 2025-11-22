package com.group3.events.domain.dto.event.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

import java.util.Date;

@Getter
public class GetFilteredEventPageReq {

    private final String token;

    private final Integer page;

    private final Integer size;

    private final String text;

    private final Date dateInit;

    private final Date dateEnd;

    private final String secret;

    private GetFilteredEventPageReq(String token, Integer page, Integer size, String text, String secret, Date dateInit, Date dateEnd) {
        this.token = token;
        this.page = page;
        this.size = size;
        this.text = text;
        this.secret = secret;
        this.dateInit = dateInit;
        this.dateEnd = dateEnd;
    }

    public static GetFilteredEventPageReq create(String token, Integer page, Integer size, String text, String secret, Date dateInit, Date dateEnd) {

        if (secret == null || secret.isBlank()) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        if (page == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        if (page < 0) {
            throw new ErrorHandler(ErrorType.INVALID_FIELDS);
        }

        if (size == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        if (size < 0) {
            throw new ErrorHandler(ErrorType.INVALID_FIELDS);
        }

        if (token == null || token.isEmpty()){
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        return new GetFilteredEventPageReq(token, page, size, text, secret, dateInit, dateEnd);
    }

}
