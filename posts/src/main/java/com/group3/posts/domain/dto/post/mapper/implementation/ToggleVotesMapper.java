package com.group3.posts.domain.dto.post.mapper.implementation;

import com.group3.entity.Post;
import com.group3.posts.domain.dto.post.request.TogglePostVotesReq;
import com.group3.posts.domain.dto.post.response.TogglePostVotesRes;

import java.util.Map;

public class ToggleVotesMapper {

    public TogglePostVotesReq toRequest(String token, Map<String , Object> payload) {
        return TogglePostVotesReq.create(
            token,
            (String) payload.get("voteType"),
            (String) payload.get("postId")
        );
    }

    public TogglePostVotesRes toResponse(Post post) {
        return new TogglePostVotesRes(
            post.getAuthor(),
            post.getId(),
            post.getPageProfile(),
            post.getImageId(),
            post.getTitle(),
            post.getContent(),
            post.getViews(),
            post.getUpvoters().size(),
            post.getDownvoters().size(),
            post.getCreatedAt()
        );
    }
}
