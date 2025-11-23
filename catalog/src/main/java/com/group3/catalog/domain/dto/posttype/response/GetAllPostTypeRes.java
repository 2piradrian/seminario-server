package com.group3.catalog.domain.dto.posttype.response;

import com.group3.entity.PostType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetAllPostTypeRes {

    private final List<PostType> postTypes;

}
