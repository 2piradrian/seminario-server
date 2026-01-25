package com.group3.results.presentation.service;

import com.group3.entity.*;
import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import com.group3.results.config.helpers.FeedUtil;
import com.group3.results.config.helpers.SecretKeyHelper;
import com.group3.results.data.repository.*;
import com.group3.results.domain.dto.mapper.ResultsMapper;
import com.group3.results.domain.dto.request.GetFeedMergedByProfileIdPageReq;
import com.group3.results.domain.dto.request.GetFeedPageReq;
import com.group3.results.domain.dto.request.GetSerchResultFilteredReq;
import com.group3.results.domain.dto.response.GetFeedMergedByProfileIdPageRes;
import com.group3.results.domain.dto.response.GetFeedPageRes;
import com.group3.results.domain.dto.response.GetSearchResultFilteredRes;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class ResultService implements ResultServiceI {

    private final SecretKeyHelper secretKeyHelper;

    private final PageProfileRepository pageProfileRepository;

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    private final EventRepository eventRepository;

    private final CatalogRepository catalogRepository;

    @Override
    public GetSearchResultFilteredRes getSearchResult(GetSerchResultFilteredReq dto) {
        User user = this.userRepository.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        List<Follow> follows = this.userRepository.getAllFollowers(dto.getToken(), user.getId(), this.secretKeyHelper.getSecret());
        if (follows == null) throw new ErrorHandler(ErrorType.USER_NOT_FOUND);

        ContentType contentType = this.catalogRepository.getContentById(dto.getContentTypeId());
        if (contentType == null) throw new ErrorHandler(ErrorType.CONTENT_TYPE_NOT_FOUND);

        String secret = this.secretKeyHelper.getSecret();
        List<User> users = new ArrayList<>();
        List<PageProfile> pageProfiles = new ArrayList<>();
        List<Post> posts = new ArrayList<>();
        List<Event> events = new ArrayList<>();

        ContentType type = this.catalogRepository.getContentById(dto.getContentTypeId());
        if (type == null) throw new ErrorHandler(ErrorType.CONTENT_TYPE_NOT_FOUND);

        if(type.isPageType()){

            pageProfiles = pageProfileRepository.getPageFilteredPage(
                dto.getToken(),
                dto.getText(),
                dto.getPageTypeId(),
                dto.getPage(),
                dto.getSize(),
                secret
            );

        } else if (type.isUserType()) {

            List<String> styleIds = dto.getStyles() != null
                ? dto.getStyles()
                : List.of();

            List<String> instrumentIds = dto.getInstruments() != null
                ? dto.getInstruments()
                : List.of();

            users = userRepository.getUserFilteredPage(
                dto.getToken(),
                dto.getText(),
                styleIds,
                instrumentIds,
                dto.getPage(),
                dto.getSize(),
                secret
            );

        } else if (type.isPostType()) {

            posts = postRepository.getFilteredPosts(
                dto.getToken(),
                dto.getPage(),
                dto.getSize(),
                dto.getText(),
                dto.getPostTypeId(),
                secret
            );

            this.enrichPosts(posts, dto.getToken(), secret);

        } else if (type.isEventType()){

            events = eventRepository.getFilteredEventsPage(
                dto.getToken(),
                dto.getPage(),
                dto.getSize(),
                dto.getText(),
                secret,
                dto.getDateInit(),
                dto.getDateEnd()
            );

            this.enrichEvents(events, dto.getToken(), secret);

        }

        return ResultsMapper.getSearchResult().toResponse(users, pageProfiles, posts, events);
    }

    @Override
    public GetFeedPageRes getFeedPage(GetFeedPageReq dto) {
        User user = this.userRepository.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        String secret = this.secretKeyHelper.getSecret();

        List<Post> posts = this.postRepository.getFilteredPosts(
            dto.getToken(),
            dto.getPage(),
            dto.getSize(),
            "",
            "",
            secret
        );

        this.enrichPosts(posts, dto.getToken(), secret);

        return ResultsMapper.getFeed().toResponse(posts);
    }

    @Override
    public GetFeedMergedByProfileIdPageRes getMergedFeedPage(GetFeedMergedByProfileIdPageReq dto) {
        User user = this.userRepository.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        String secret = this.secretKeyHelper.getSecret();

        int postsLimit = (int) Math.ceil(dto.getSize() / 2.0);
        int eventsLimit = dto.getSize() - postsLimit;

        List<Post> posts = this.postRepository.getOnlyPagePosts(
            dto.getToken(),
            secret,
            dto.getPage(),
            postsLimit
        );

        List<Event> events = this.eventRepository.getOnlyPageEvents(
            dto.getToken(),
            secret,
            dto.getPage(),
            eventsLimit
        );

        this.enrichPosts(posts, dto.getToken(), secret);
        this.enrichEvents(events, dto.getToken(), secret);

        List<Object> feed = new ArrayList<>();
        feed.addAll(posts);
        feed.addAll(events);

        feed.sort(Comparator.comparing(FeedUtil::getCreatedAt).reversed());

        return ResultsMapper.getFeedMerged().toResponse(feed);
    }

    // ======== Helper Methods for Batch Enrichment ========

    private void enrichPosts(List<Post> posts, String token, String secret) {
        if (posts == null || posts.isEmpty()) return;

        Set<String> userIds = new HashSet<>();
        Set<String> pageIds = new HashSet<>();

        for (Post post : posts) {
            if (post.getAuthor() != null && post.getAuthor().getId() != null) {
                userIds.add(post.getAuthor().getId());
            }
            if (post.getPageProfile() != null && post.getPageProfile().getId() != null) {
                pageIds.add(post.getPageProfile().getId());
            }
        }

        Map<String, User> userMap = this.fetchUsersMap(userIds, token, secret);
        Map<String, PageProfile> pageMap = this.fetchPagesMap(pageIds, secret);

        posts.removeIf(post -> {
            boolean isValid = true;

            if (post.getAuthor() != null && post.getAuthor().getId() != null) {
                User author = userMap.get(post.getAuthor().getId());

                if (author == null) {
                    isValid = false;
                } else {
                    post.setAuthor(author);
                }
            }

            if (isValid && post.getPageProfile() != null && post.getPageProfile().getId() != null) {
                PageProfile page = pageMap.get(post.getPageProfile().getId());

                if (page == null) {
                    isValid = false;
                } else {
                    post.setPageProfile(page);
                }
            }

            return !isValid;
        });
    }

    private void enrichEvents(List<Event> events, String token, String secret) {
        if (events == null || events.isEmpty()) return;

        Set<String> userIds = new HashSet<>();
        Set<String> pageIds = new HashSet<>();

        for (Event event : events) {
            if (event.getAuthor() != null && event.getAuthor().getId() != null) {
                userIds.add(event.getAuthor().getId());
            }
            if (event.getPageProfile() != null && event.getPageProfile().getId() != null) {
                pageIds.add(event.getPageProfile().getId());
            }
        }

        Map<String, User> userMap = this.fetchUsersMap(userIds, token, secret);
        Map<String, PageProfile> pageMap = this.fetchPagesMap(pageIds, secret);

        events.removeIf(event -> {
            boolean isValid = true;

            if (event.getAuthor() != null && event.getAuthor().getId() != null) {
                User author = userMap.get(event.getAuthor().getId());

                if (author == null) {
                    isValid = false;
                } else {
                    event.setAuthor(author);
                }
            }

            if (isValid && event.getPageProfile() != null && event.getPageProfile().getId() != null) {
                PageProfile page = pageMap.get(event.getPageProfile().getId());

                if (page == null) {
                    isValid = false;
                } else {
                    event.setPageProfile(page);
                }
            }

            return !isValid;
        });
    }

    private Map<String, User> fetchUsersMap(Set<String> userIds, String token, String secret) {
        if (userIds.isEmpty()) return Collections.emptyMap();

        List<User> users = this.userRepository.getByListOfIds(
            token,
            secret,
            1,
            userIds.size(),
            new ArrayList<>(userIds)
        );

        return users.stream()
            .collect(
                Collectors.toMap(
                    User::getId,
                    page -> page,
                    (a, b) -> a)
            );
    }

    private Map<String, PageProfile> fetchPagesMap(Set<String> pageIds, String secret) {
        if (pageIds.isEmpty()) return Collections.emptyMap();

        List<PageProfile> pages = this.pageProfileRepository.getListByIds(
            new ArrayList<>(pageIds),
            secret
        );

        return pages.stream().
            collect(
                Collectors.toMap(
                    PageProfile::getId,
                    page -> page,
                    (a, b) -> a)
            );
    }

}
