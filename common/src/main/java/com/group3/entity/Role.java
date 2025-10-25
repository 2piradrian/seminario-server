package com.group3.entity;

public enum Role {
    USER,
    MODERATOR,
    ADMIN;

    public boolean canDelete() {
        return this == MODERATOR || this == ADMIN;
    }

    public boolean canAsignRole() {
        return this == ADMIN;
    }

    public static Role fromString(String roleId) {
        try {
            return Role.valueOf(roleId.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
