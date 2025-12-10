package com.group3.results.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class GetFeedMergedCursorPageRes {

    private final List<?> content;

    private final LocalDateTime nextCursor;

}
