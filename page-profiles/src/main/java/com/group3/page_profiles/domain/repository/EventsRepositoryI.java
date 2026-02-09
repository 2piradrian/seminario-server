package com.group3.page_profiles.domain.repository;

public interface EventsRepositoryI {
    void deleteEventsByPageId(String pageId, String secret);
}