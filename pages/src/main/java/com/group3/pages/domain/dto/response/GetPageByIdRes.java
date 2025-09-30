package com.group3.pages.domain.dto.response;

import com.group3.entity.PageType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetPageByIdRes {
    
    private final String id;

    private final String name;

    private final String portraitImageId;

    private final String profileImageId;

    private final String shortDescription;

    private final String longDescription;

    private final String ownerId;

    private final List<String> members;

    private PageType pageType;
    
}
