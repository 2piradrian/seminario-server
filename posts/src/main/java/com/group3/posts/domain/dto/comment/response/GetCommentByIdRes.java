package com.group3.posts.domain.dto.comment.response;

import com.group3.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetCommentByIdRes {
    private final Comment comment;
}
