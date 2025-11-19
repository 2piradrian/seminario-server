package com.group3.catalog.domain.dto.posttype.response;

import com.group3.entity.PostType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetPostTypeByIdRes {

    private final PostType postType;

}
