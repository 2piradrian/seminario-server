package com.group3.results.domain.repository;

import com.group3.entity.Event;

import java.util.List;

public interface EventRepositoryI {

    List<Event> getFilteredEventsPage(String token, Integer page, Integer size, String text, String secret, String dateInit, String dateEnd);

    List<Event> getOnlyPageEvents(String token, String secret, Integer page, Integer size);

}
