package com.group3.entity;

public enum Vote {
    UPVOTE,
    DOWNVOTE;

    public static Vote fromString(String voteId) {
        try {
            return Vote.valueOf(voteId.toUpperCase());
        }
        catch (IllegalArgumentException e) {
            return null;
        }
    }

}
