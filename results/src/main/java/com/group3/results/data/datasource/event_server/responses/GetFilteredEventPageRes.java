package com.group3.results.data.datasource.event_server;

import com.group3.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetFilteredPostPageRes {

    private final List<Post> posts;

    private final Integer nextPage;

}
