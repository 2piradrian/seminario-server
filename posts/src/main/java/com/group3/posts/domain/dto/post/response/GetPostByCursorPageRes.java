package com.group3.posts.domain.dto.post.response;

import com.group3.entity.Event;
import com.group3.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class GetPostByCursorPageRes {

    private final List<Post> posts;

    private final LocalDateTime nextCursor;

}
