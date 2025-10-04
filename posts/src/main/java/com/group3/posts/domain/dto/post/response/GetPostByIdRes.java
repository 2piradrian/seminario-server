package com.group3.posts.domain.dto.post.response;

import com.group3.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class GetPostByIdRes {

    private final String authorId;

    private final String postId;

    private final String pageId;

    private final String imageId;

    private  final String title;

    private final String content;

    private final Integer views;

    private final Integer upvoters;

    private final Integer downvoters;

    private final LocalDateTime createdAt;

}
