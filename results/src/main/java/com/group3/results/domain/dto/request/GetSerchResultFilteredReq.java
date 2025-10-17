package com.group3.results.domain.dto.request;

import com.group3.entity.Instrument;
import com.group3.entity.Style;
import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

import java.util.List;

@Getter
public class GetSerchResultFilteredReq {

    private final String token;

    private final Integer page;

    private final Integer size;

    private final String name;

    private final List<Style> styles;

    private final List<Instrument> instruments;

    private final List<String> ids;

    private final String pageTypeId;

    public GetSerchResultFilteredReq(String token, Integer page, Integer size, String name, List<Style> styles, List<Instrument> instruments, List<String> ids, String pageTypeId) {
        this.token = token;
        this.page = page;
        this.size = size;
        this.name = name;
        this.styles = styles;
        this.instruments = instruments;
        this.ids = ids;
        this.pageTypeId = pageTypeId;
    }

    public static GetSerchResultFilteredReq create(String token, Integer page, Integer size, String name, List<Style> styles, List<Instrument> instruments, List<String> ids, String pageTypeId) {

        if (token == null || token.isBlank()) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        if (name == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
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

        if (styles == null || instruments == null || ids == null){
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        if (pageTypeId == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        return new GetSerchResultFilteredReq(token, page, size, name, styles, instruments, ids, pageTypeId);
    }

}
