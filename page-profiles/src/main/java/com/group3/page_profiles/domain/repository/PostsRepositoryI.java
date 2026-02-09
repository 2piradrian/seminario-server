package com.group3.page_profiles.domain.repository;

public interface PostsRepositoryI {
    void deletePostsByPageId(String pageId, String secret);
}
