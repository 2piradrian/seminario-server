package com.group3.posts.domain.repository;

import com.group3.entity.CursorContent;
import com.group3.entity.PageContent;
import com.group3.entity.Post;

import java.time.LocalDateTime;

public interface PostRepositoryI {

    Post getById(String postId);

    PageContent<Post> getAllPosts(Integer page, Integer size);

    CursorContent<Post> getByCursorPage(LocalDateTime cursor, Integer size, String profileId);

    PageContent<Post> getPostsByAuthorId(String userId, Integer page, Integer size);

    PageContent<Post> getPostsByPageId(String pageId, Integer page, Integer size);

    PageContent<Post> getFilteredPosts(Integer page, Integer size, String text, String postTypeId);

    Post save(Post post);

    Post update(Post post);

}
