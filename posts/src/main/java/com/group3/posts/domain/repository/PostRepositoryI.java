package com.group3.posts.domain.repository;

import com.group3.entity.PageContent;
import com.group3.entity.Post;

public interface PostRepositoryI {

    Post getById(String postId);

    PageContent<Post> getAllPosts(Integer page, Integer size);

    PageContent<Post> getOnlyPagePosts(Integer page, Integer size);

    PageContent<Post> getByProfileIdPage(String profileId, Integer page, Integer size);

    PageContent<Post> getFilteredPosts(Integer page, Integer size, String text, String postTypeId);

    Post save(Post post);

    Post update(Post post);

    void deleteById(String id);

}
