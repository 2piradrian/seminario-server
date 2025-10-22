package com.group3.results.domain.repository;

import com.group3.entity.Post;

import java.util.List;

public interface PostRepositoryI {

    List<Post> getFilteredPosts(Integer page, Integer size, String text, String secret);

}
