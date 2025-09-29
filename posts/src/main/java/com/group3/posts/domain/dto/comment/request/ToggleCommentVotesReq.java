package com.group3.posts.domain.dto.comment.request;

import com.group3.entity.Vote;
import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class ToggleCommentVotesReq {

    private final String token;

    private final Vote voteType;

    private final String commentId;

    public ToggleCommentVotesReq(String token, Vote voteType, String commentId) {
        this.token = token;
        this.voteType = voteType;
        this.commentId = commentId;
    }

    public static ToggleCommentVotesReq create(String token, String voteType, String commentId) {

        if (token == null) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        if (voteType == null || commentId == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        Vote voteEnum = null;
        boolean isValidVote = false;
        for (Vote c : Vote.values()) {
            if (c.name().equals(voteType)) {
                isValidVote = true;
                voteEnum = c;
                break;
            }
        }
        if (!isValidVote) {
            throw new ErrorHandler(ErrorType.INVALID_FIELDS);
        }

        return new ToggleCommentVotesReq(token, voteEnum, commentId);
    }
}
