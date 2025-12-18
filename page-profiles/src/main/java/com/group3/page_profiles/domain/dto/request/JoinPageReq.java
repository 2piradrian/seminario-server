package com.group3.page_profiles.domain.dto.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;


@Getter
public class JoinPageReq {

    private final String token;

    private final String invitationToken;

    private final String pageId;

    private JoinPageReq(
        String token,
        String invitationToken,
        String pageId
    ) {
        this.token = token;
        this.invitationToken = invitationToken;
        this.pageId = pageId;
    }

    public static JoinPageReq create(
        String token,
        String invitationToken,
        String pageId
    ) {

        if (token == null || token.isEmpty()) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        if (invitationToken == null || invitationToken.isEmpty()) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        if (pageId == null || pageId.isEmpty()){
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        return new JoinPageReq(
            token,
            invitationToken,
            pageId
        );
    }

}
