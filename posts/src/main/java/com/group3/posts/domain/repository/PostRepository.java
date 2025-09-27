package com.group3.posts.domain.repository;

import com.group3.entity.Category;
import com.group3.entity.PageContent;
import com.group3.entity.Post;

import java.util.List;

public interface PostRepository {

    Post getById(String postId);

    PageContent<Post> getAllPosts(Integer size, Category category);

    List<Post >getMonthlyPosts (Integer month, Integer year);

    Post save(Post post);

    Post update(Post post);
}
