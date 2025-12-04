package com.group3.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PostTypeEnum {

    MARKET("Market"),
    JOB("Job"),
    GENERAL("General");

    private String name;

    public static PostTypeEnum fromName(String name) {
        if (name == null) {
            return null;
        }

        for (PostTypeEnum type : PostTypeEnum.values()) {
            if (type.getName().equalsIgnoreCase(name)) {
                return type;
            }
        }

        return null;
    }

}
