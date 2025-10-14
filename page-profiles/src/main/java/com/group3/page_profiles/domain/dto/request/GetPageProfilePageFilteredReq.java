package com.group3.page_profiles.domain.dto.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

import java.util.List;

@Getter
public class GetPageProfilePageFilteredReq {

    private final String secret;

    private final Integer page;

    private final Integer size;

    private final String name;

    private final String pageTypeId;

    private final List<String> memberIds;

    private GetPageProfilePageFilteredReq(String secret, Integer page, Integer size, String name, String pageTypeId, List<String> memberIds) {
        this.secret = secret;
        this.page = page;
        this.size = size;
        this.name = name;
        this.pageTypeId = pageTypeId;
        this.memberIds = memberIds;
    }

    public static GetPageProfilePageFilteredReq create(String secret, Integer page, Integer size, String name, String pageTypeId, List<String> memberIds) {

        if (secret == null) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        if (page == null || page < 1) {
            throw new ErrorHandler(ErrorType.INVALID_FIELDS);
        }

        if (size == null || size < 1) {
            throw new ErrorHandler(ErrorType.INVALID_FIELDS);
        }

        if (name == null || pageTypeId == null || memberIds == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        return new GetPageProfilePageFilteredReq(secret, page, size, name, pageTypeId, memberIds);
    }

}
