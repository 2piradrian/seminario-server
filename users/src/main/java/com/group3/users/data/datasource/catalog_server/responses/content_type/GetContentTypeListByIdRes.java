package com.group3.users.data.datasource.catalog_server.responses.content_type;

import com.group3.entity.ContentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetContentTypeListByIdRes {

    private List<ContentType> contentTypes;

}
