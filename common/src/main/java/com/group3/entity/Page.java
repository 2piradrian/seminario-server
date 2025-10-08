package com.group3.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Page {

    private String id;

    private String name;

    private String portraitImage;

    private String profileImage;

    private String shortDescription;

    private String longDescription;

    private UserProfile owner;

    private List<UserProfile> members;

    private Status status;

    private PageType pageType;

}
