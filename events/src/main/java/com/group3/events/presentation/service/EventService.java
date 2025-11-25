package com.group3.events.presentation.service;

import com.group3.config.PrefixedUUID;
import com.group3.entity.*;
import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import com.group3.events.config.helpers.SecretKeyHelper;
import com.group3.events.data.repository.*;
import com.group3.events.domain.dto.event.mapper.EventMapper;
import com.group3.events.domain.dto.event.request.*;
import com.group3.events.domain.dto.event.response.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class EventService implements EventServiceI {

    private final SecretKeyHelper secretKeyHelper;

    private final EventRepository eventRepository;

    private final UserRepository userRepository;

    private final ImagesRepository imagesRepository;

    private final PageProfileRepository pageProfileRepository;

    @Override
    public CreateEventRes create(CreateEventReq dto) {
        User user = this.userRepository.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        Event event = new Event();

        PrefixedUUID.EntityType type = PrefixedUUID.resolveType(UUID.fromString(dto.getProfileId()));
        if (type == PrefixedUUID.EntityType.USER) {
            if (!user.getId().equals(dto.getProfileId())) {
                throw new ErrorHandler(ErrorType.UNAUTHORIZED);
            }
            event.setPageProfile(PageProfile.builder().id(null).build());
        }
        else if (type == PrefixedUUID.EntityType.PAGE) {
            PageProfile page = this.pageProfileRepository.getById(dto.getProfileId(), dto.getToken());
            if (page.getMembers().stream().noneMatch(member -> member.getId().equals(user.getId()))) {
                throw new ErrorHandler(ErrorType.UNAUTHORIZED);
            }
            event.setPageProfile(page);
        }

        if (dto.getImage() != null) {
            String imageId = this.imagesRepository.upload(dto.getImage(), secretKeyHelper.getSecret());
            event.setImageId(imageId);
        }

        event.setId(PrefixedUUID.generate(PrefixedUUID.EntityType.EVENT).toString());
        event.setAuthor(user);
        event.setTitle(dto.getTitle());
        event.setContent(dto.getContent());
        event.setDateInit(dto.getDateInit());
        event.setDateEnd(dto.getDateEnd());
        event.setStatus(Status.ACTIVE);
        event.setViews(0);
        event.setAssists(List.of(user.getId()));
        event.setCreatedAt(LocalDateTime.now());
        event.setUpdatedAt(LocalDateTime.now());

        Event saved = this.eventRepository.save(event);
        return EventMapper.create().toResponse(saved);
    }

    @Override
    public GetEventByIdRes getById(GetEventByIdReq dto) {
        User user = userRepository.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        Event event = this.eventRepository.getById(dto.getEventId());
        if (event == null) throw new ErrorHandler(ErrorType.EVENT_NOT_FOUND);

        if (event.getAuthor().getId() != null) {
            User fullAuthor = this.userRepository.getById(event.getAuthor().getId(), dto.getToken());
            event.setAuthor(fullAuthor);
        }
        if (event.getPageProfile().getId() != null) {
            PageProfile fullPage = this.pageProfileRepository.getById(event.getPageProfile().getId(), dto.getToken());
            event.setPageProfile(fullPage);
        }

        Integer views = event.getViews();
        event.setViews(views + 1);

        this.eventRepository.update(event);

        event.calculateAssistsQuantity();
        event.setIsAssisting(user.getId());

        return EventMapper.getById().toResponse(event);
    }

    // ======== Get Filtered Events ========

    @Override
    public GetFilteredEventPageRes getFilteredEvents(GetFilteredEventPageReq dto) {
        User user = userRepository.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        if (!this.secretKeyHelper.isValid(dto.getSecret())) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        PageContent<Event> events = this.eventRepository.getFilteredEvents(
            dto.getPage(),
            dto.getSize(),
            dto.getText(),
            dto.getDateInit(),
            dto.getDateEnd()
        );

        return EventMapper.getFilteredPage().toResponse(events);
    }

    @Override
    public GetEventAndAssistsPageRes getEventsAndAssistsById(GetEventAndAssistsPageReq dto) {
        User user = this.userRepository.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        PageContent<Event> events = this.eventRepository.getByAuthorOrAssistant(dto.getUserId(), dto.getPage(), dto.getSize());

        for (Event event : events.getContent()) {
            if (event.getAuthor().getId() != null) {
                User fullAuthor = this.userRepository.getById(event.getAuthor().getId(), dto.getToken());
                event.setAuthor(fullAuthor);
            }
            if (event.getPageProfile().getId() != null) {
                PageProfile fullPage = this.pageProfileRepository.getById(event.getPageProfile().getId(), dto.getToken());
                event.setPageProfile(fullPage);
            }
            event.calculateAssistsQuantity();
            event.setIsAssisting(user.getId());
        }

        return EventMapper.getEventAndAssistsMapper().toResponse(events);
    }

    @Override
    public ToggleAssistRes toggleAssist(ToggleAssistReq dto) {
        User user = this.userRepository.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        Event event = this.eventRepository.getById(dto.getEventId());
        if (event == null) throw new ErrorHandler(ErrorType.EVENT_NOT_FOUND);

        if (event.getAuthor().getId().equals(user.getId())) throw new ErrorHandler(ErrorType.USER_ALREADY_IS_AUTHOR);

        LocalDateTime actualDateTime = LocalDateTime.now();

        LocalDateTime eventDateEnd = event.getDateEnd().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        if (actualDateTime.isAfter(eventDateEnd)) throw new ErrorHandler(ErrorType.EVENT_ALREADY_ENDED);

        String userId = user.getId();
        List<String> updateAssists = event.getAssists();

        if (updateAssists.contains(userId)) {
            updateAssists.remove(userId);
        }
        else {
            updateAssists.add(userId);
        }

        event.setAssists(updateAssists);
        event.setIsAssisting(userId);

        this.eventRepository.update(event);

        if (event.getAuthor() != null && event.getAuthor().getId() != null) {
            User fullAuthor = this.userRepository.getById(event.getAuthor().getId(), dto.getToken());
            event.setAuthor(fullAuthor);
        }

        if (event.getPageProfile() != null && event.getPageProfile().getId() != null) {
            PageProfile fullPage = this.pageProfileRepository.getById(event.getPageProfile().getId(), dto.getToken());
            event.setPageProfile(fullPage);
        }

        return EventMapper.toggleAssist().toResponse(event);
    }

    @Override
    public EditEventRes edit(EditEventReq dto) {
        User user = this.userRepository.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        Event event = this.eventRepository.getById(dto.getEventId());
        if (event == null) throw new ErrorHandler(ErrorType.EVENT_NOT_FOUND);

        if (!event.getAuthor().getId().equals(user.getId())) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        if (dto.getBase64Image() != null) {
            String eventImage = event.getImageId();
            if (eventImage != null) {
                this.imagesRepository.delete(eventImage, secretKeyHelper.getSecret());
            }
            String imageId = this.imagesRepository.upload(dto.getBase64Image(), secretKeyHelper.getSecret());
            event.setImageId(imageId);
        }

        event.setTitle(dto.getTitle());
        event.setContent(dto.getContent());
        event.setDateInit(dto.getDateInit());
        event.setDateEnd(dto.getDateEnd());
        event.setUpdatedAt(LocalDateTime.now());

        Event edited = this.eventRepository.update(event);

        return EventMapper.edit().toResponse(edited);
    }

    @Override
    public void delete(DeleteEventReq dto) {
        User user = this.userRepository.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        Event event = this.eventRepository.getById(dto.getEventId());
        if (event == null) throw new ErrorHandler(ErrorType.EVENT_NOT_FOUND);

        if (event.getAuthor() == null || !event.getAuthor().getId().equals(user.getId())) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        event.setUpdatedAt(LocalDateTime.now());
        event.setStatus(Status.DELETED);

        this.eventRepository.update(event);
    }
}
