package com.group3.user_profiles.domain.dto.profile.request;

import com.group3.entity.Instrument;
import com.group3.entity.Style;
import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

import java.util.List;

@Getter
public class GetUserProfilePageFilteredReq {

    private final String token;

    private final String secret;

    private final Integer page;

    private final Integer size;

    private final String fullname;

    private final List<Style> styles;

    private final List<Instrument> instruments;

    private final List<String> ids;

    private GetUserProfilePageFilteredReq(String token, String secret, Integer page, Integer size, String fullname, List<Style> styles, List<Instrument> instruments, List<String> ids) {
        this.token = token;
        this.secret = secret;
        this.page = page;
        this.size = size;
        this.fullname = fullname;
        this.styles = styles;
        this.instruments = instruments;
        this.ids = ids;
    }

    public static GetUserProfilePageFilteredReq create(String token, String secret, Integer page, Integer size, String fullname, List<Style> styles, List<Instrument> instruments, List<String> ids) {

        if (token == null) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        if (secret == null) {
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

        if (fullname == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        if ( styles == null || instruments == null || ids == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        return new GetUserProfilePageFilteredReq(token, secret, page, size, fullname, styles, instruments, ids);
    }

}
