package com.group3.entity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ContentTypeEnum {

    POST("post"),
    USERPROFILE("userprofile"),
    PAGEPROFILE("pageprofile"),
    EVENT("event");

    private String name;

    public boolean isName(String contentType) {
        if (contentType == null) {
            return false;
        }
        return this.name().toLowerCase().equals(contentType);
    }

}
