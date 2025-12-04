package com.group3.posts.domain.dto.post.response;

import com.group3.entity.PageProfile;
import com.group3.entity.PostType;
import com.group3.entity.PostTypeEnum;
import com.group3.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class TogglePostVotesRes {

    private final User author;

    private final String postId;

    private final PageProfile pageProfile;

    private final String imageId;

    private final String title;

    private final String content;

    private final Integer views;

    private final Integer upvotersQuantity;

    private final Integer downvotersQuantity;

    private final LocalDateTime createdAt;

    private final PostTypeEnum postType;

}
