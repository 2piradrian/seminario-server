package com.group3.posts.domain.dto.post.response;

import com.group3.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetOwnPostPageRes {

    private final List<Post> posts;

    private final Integer nextPage;

}
