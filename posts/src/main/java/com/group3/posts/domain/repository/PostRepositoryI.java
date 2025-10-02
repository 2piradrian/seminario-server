package com.group3.posts.domain.repository;

import com.group3.entity.Category;
import com.group3.entity.PageContent;
import com.group3.entity.Post;

import java.util.List;

public interface PostRepositoryI {

    Post getById(String postId);

    PageContent<Post> getAllPosts(Integer page, Integer size);

    Post save(Post post);

    Post update(Post post);

}
