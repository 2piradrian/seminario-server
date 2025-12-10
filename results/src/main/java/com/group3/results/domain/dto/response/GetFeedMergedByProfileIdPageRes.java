package com.group3.results.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetFeedMergedByProfileIdPageRes {

    private final List<?> content;

}
