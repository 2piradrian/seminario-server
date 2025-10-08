package com.group3.posts.domain.dto.post.response;

import com.group3.entity.Page;
import com.group3.entity.Post;
import com.group3.entity.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@AllArgsConstructor
public class TogglePostVotesRes {

    private final UserProfile author;

    private final String postId;

    private final Page page;

    private final String imageId;

    private  final String title;

    private final String content;

    private final Integer views;

    private final Set<String> upvoters;

    private final Set<String> downvoters;

    private final LocalDateTime createdAt;

}
