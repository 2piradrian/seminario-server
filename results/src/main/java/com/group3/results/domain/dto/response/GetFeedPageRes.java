package com.group3.results.domain.dto.response;

import com.group3.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetFeedPageRes {

    private final List<Post> posts;

}
