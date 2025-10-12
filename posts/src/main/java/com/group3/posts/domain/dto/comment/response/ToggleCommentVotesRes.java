package com.group3.posts.domain.dto.comment.response;

import com.group3.entity.Comment;
import com.group3.entity.PageProfile;
import com.group3.entity.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class ToggleCommentVotesRes {

    private final String commentId;

    private final UserProfile author;

    private final String postId;

    private final Comment replyTo;

    private final String content;

    private List<String> upvoters;

    private List<String> downvoters;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;

    private PageProfile page;

}
