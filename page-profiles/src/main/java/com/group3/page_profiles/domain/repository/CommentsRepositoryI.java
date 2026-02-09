package com.group3.page_profiles.domain.repository;

public interface CommentsRepositoryI {
    void deleteCommentsByPageId(String pageId, String secret);
}