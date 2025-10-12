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

    private String commentId;

    private UserProfile author;

    private String postId;

    private Comment replyTo;

    private String content;

    private List<String> upvoters;

    private List<String> downvoters;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private PageProfile page;

}
