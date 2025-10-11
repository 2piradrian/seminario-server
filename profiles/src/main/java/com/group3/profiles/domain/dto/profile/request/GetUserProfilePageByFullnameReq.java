package com.group3.profiles.domain.dto.profile.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class GetUserProfilePageByFullnameReq {

    private final Integer page;

    private final Integer size;

    private final String fullname;

    private GetUserProfilePageByFullnameReq(Integer page, Integer size, String fullname) {
        this.page = page;
        this.size = size;
        this.fullname = fullname;
    }

    public static GetUserProfilePageByFullnameReq create(Integer page, Integer size, String fullname) {

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

        if (fullname == null || fullname.isBlank()) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        return new GetUserProfilePageByFullnameReq(page, size, fullname);
    }

}
