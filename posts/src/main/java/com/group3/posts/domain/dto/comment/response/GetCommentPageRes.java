package com.group3.posts.domain.dto.comment.response;

import com.group3.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetCommentPageRes {

    List<Comment> comments;

    Integer nextPage;
}
