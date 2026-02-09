package com.group3.entity;

public enum NotificationContent {
    COMMENT,
    UPVOTE,
    PAGE_INVITATION,
    DOWNVOTE,
    ASSIST,
    MODERATION,
    FOLLOW;

    public static NotificationContent fromString(String contentId) {
        try {
            return NotificationContent.valueOf(contentId.toUpperCase());
        }
        catch (IllegalArgumentException e) {
            return null;
        }
    }

}
