package com.group3.results.domain.repository;

import com.group3.entity.Event;

import java.util.Date;
import java.util.List;

public interface EventRepositoryI {

    List<Event> getFilteredEvents(Integer page, Integer size, String text, String secret, Date dateInit, Date dateEnd);

}
