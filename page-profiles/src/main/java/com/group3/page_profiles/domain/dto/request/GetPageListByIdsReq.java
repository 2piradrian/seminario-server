package com.group3.page_profiles.domain.dto.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

import java.util.List;

@Getter
public class GetPageListByIdsReq {

    private final List<String> pageIds;
    private final String secret;

    private GetPageListByIdsReq(List<String> pageIds, String secret) {
        this.pageIds = pageIds;
        this.secret = secret;
    }

    public static GetPageListByIdsReq create(List<String> pageIds, String secret) {

        if (pageIds == null || pageIds.isEmpty() || secret == null || secret.isEmpty()) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        return new GetPageListByIdsReq(pageIds, secret);
    }
}
