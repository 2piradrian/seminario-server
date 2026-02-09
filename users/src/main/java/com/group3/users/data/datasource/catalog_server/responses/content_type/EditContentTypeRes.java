package com.group3.users.data.datasource.catalog_server.responses.content_type;

import com.group3.entity.ContentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EditContentTypeRes {
    private ContentType contentType;
}
