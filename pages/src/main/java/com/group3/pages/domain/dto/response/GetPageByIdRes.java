package com.group3.pages.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetPageByIdRes {
    
    private final String id;

    private final String name;

    private final String imageId;

    private final String ownerId;

    private final List<String> members;
    
}
