package com.group3.catalog.domain.dto.contentType.response;

import com.group3.entity.ContentType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetAllContentTypeRes {

    List<ContentType> contentTypes;

}
