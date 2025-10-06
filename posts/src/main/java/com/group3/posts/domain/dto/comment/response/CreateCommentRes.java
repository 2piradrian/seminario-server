package com.group3.posts.domain.dto.comment.response;


import com.group3.entity.Comment;
import com.group3.entity.Page;
import com.group3.entity.Status;
import com.group3.entity.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@AllArgsConstructor
public class CreateCommentRes {

    private String id;

    private UserProfile author;

    private String postId;

    private Comment replyTo;

    private String content;

    private Set<String> upvoters;

    private Set<String> downvoters;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Page page;

}
