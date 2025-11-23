package com.group3.results.domain.dto.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
public class GetSerchResultFilteredReq {

    private final String token;

    private final Integer page;

    private final Integer size;

    private final String text;

    private final List<String> styles;

    private final List<String> instruments;

    private final String pageTypeId;

    private final String postTypeId;

    private final String contentTypeId;

    private final String dateInit;

    private final String dateEnd;

    public GetSerchResultFilteredReq(
        String token,
        Integer page,
        Integer size,
        String text,
        List<String> styles,
        List<String> instruments,
        String contentTypeId,
        String pageTypeId,
        String postTypeId,
        String dateInit,
        String dateEnd
    ) {
            this.token = token;
            this.page = page;
            this.size = size;
            this.text = text;
            this.styles = styles;
            this.instruments = instruments;
            this.contentTypeId = contentTypeId;
            this.pageTypeId = pageTypeId;
            this.postTypeId = postTypeId;
            this.dateInit = dateInit;
            this.dateEnd = dateEnd;
    }

    public static GetSerchResultFilteredReq create(
        String token,
        Integer page,
        Integer size,
        String text,
        List<String> styles,
        List<String> instruments,
        String contentTypeId,
        String pageTypeId,
        String postTypeId,
        String dateInit,
        String dateEnd
    ) {

        if (token == null || token.isBlank()) {
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

        if (contentTypeId == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        return new GetSerchResultFilteredReq(
            token,
            page,
            size,
            text,
            styles,
            instruments,
            contentTypeId,
            pageTypeId,
            postTypeId,
            dateInit,
            dateEnd
        );
    }

}
