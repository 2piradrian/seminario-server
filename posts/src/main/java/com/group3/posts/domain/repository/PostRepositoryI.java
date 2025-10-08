package com.group3.posts.domain.repository;

import com.group3.entity.PageContent;
import com.group3.entity.Post;

public interface PostRepositoryI {

    Post getById(String postId);

    PageContent<Post> getAllPosts(Integer page, Integer size);

    Post save(Post post);

    Post update(Post post);

    PageContent<Post> getPostsByUserId(String userId, Integer page, Integer size);

    PageContent<Post> getPostsByPageId(String pageId, Integer page, Integer size);

}
