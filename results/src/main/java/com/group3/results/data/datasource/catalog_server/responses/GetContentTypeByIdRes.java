package com.group3.results.data.datasource.catalog_server.responses;

import com.group3.entity.ContentType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetContentTypeByIdRes {

    private final ContentType contentType;

}
