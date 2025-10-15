package com.group3.results.domain.repository;

import com.group3.entity.Post;

import java.util.List;

public interface PostRepositoryI {

    List<Post> getFilteredPosts(List<String> ids, Integer page, Integer size, String secret);

}
