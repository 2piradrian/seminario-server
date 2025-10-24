package com.group3.entity;

public enum Role {
    USER,
    MODERATOR,
    ADMIN;

    public Boolean canDelete() {
        return this == MODERATOR || this == ADMIN;
    }

    public Boolean canAsignRole() {
        return this == ADMIN;
    }
}
