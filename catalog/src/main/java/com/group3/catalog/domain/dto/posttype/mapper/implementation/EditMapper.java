package com.group3.catalog.domain.dto.posttype.mapper.implementation;

import com.group3.catalog.domain.dto.posttype.request.EditPostTypeReq;
import com.group3.catalog.domain.dto.posttype.response.EditPostTypeRes;
import com.group3.entity.PostType;

import java.util.Map;

public class EditMapper {

    public EditPostTypeReq toRequest(String token, String id, Map<String, Object> payload) {
        return EditPostTypeReq.create(
                token,
                id,
                (String) payload.get("name")
        );
    }

    public EditPostTypeRes toResponse(PostType postType) {
        return new EditPostTypeRes(postType);
    }
}
