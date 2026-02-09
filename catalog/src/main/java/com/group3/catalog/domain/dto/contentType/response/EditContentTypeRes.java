package com.group3.catalog.domain.dto.contentType.response;

import com.group3.entity.ContentType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EditContentTypeRes {
    private final ContentType contentType;
}
