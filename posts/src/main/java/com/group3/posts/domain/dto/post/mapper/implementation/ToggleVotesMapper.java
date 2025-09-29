package com.group3.posts.domain.dto.post.mapper.implementation;

import com.group3.posts.domain.dto.post.request.TogglePostVotesReq;

import java.util.Map;

public class ToggleVotesMapper {

    public TogglePostVotesReq toRequest(String token, Map<String , Object> payload) {
        return TogglePostVotesReq.create(
                token,
                (String) payload.get("voteType"),
                (String) payload.get("postId")
        );
    }
}
