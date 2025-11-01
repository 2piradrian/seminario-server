package com.group3.posts.domain.dto.comment.response;

import com.group3.entity.Comment;
import com.group3.entity.PageProfile;
import com.group3.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ToggleCommentVotesRes {

    private final String commentId;

    private final User author;

    private final String postId;

    private final Comment replyTo;

    private final String content;

    private Integer upvotersQuantity;

    private Integer downvotersQuantity;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;

    private PageProfile page;

}
