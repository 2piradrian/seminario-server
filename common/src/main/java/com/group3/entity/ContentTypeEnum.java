package com.group3.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ContentTypeEnum {

    POST("post"),
    USERPROFILE("userprofile"),
    PAGEPROFILE("pageprofile"),
    EVENT("event");

    private String name;

    public static ContentTypeEnum fromName(String name) {
        if (name == null) {
            return null;
        }

        for (ContentTypeEnum type : ContentTypeEnum.values()) {
            if (type.getName().equalsIgnoreCase(name)) {
                return type;
            }
        }

        return null;
    }

}
