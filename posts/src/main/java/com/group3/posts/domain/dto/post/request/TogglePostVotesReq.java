package com.group3.posts.domain.dto.post.request;

import com.group3.entity.Vote;
import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

@Getter
public class TogglePostVotesReq {

    private final String token;

    private final Vote voteType;

    private final String postId;

    public TogglePostVotesReq(String token, Vote voteType, String postId) {
        this.token = token;
        this.voteType = voteType;
        this.postId = postId;
    }

    public static TogglePostVotesReq create(String token, String voteType, String  postId) {
        if (token == null) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        if (voteType == null || postId == null) {
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

        return new TogglePostVotesReq(token, voteEnum, postId);
    }

}
