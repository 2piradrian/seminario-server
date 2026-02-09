package com.group3.users.domain.repository;

public interface EventsRepositoryI {

    void delete(String token, String eventId);

    void deleteByUserId(String token, String userId);

}
