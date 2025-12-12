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

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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

            for (Post post : posts) {
                if (post.getAuthor() != null && post.getAuthor().getId() != null) {
                    User author = userRepository.getById(post.getAuthor().getId(), dto.getToken());
                    post.setAuthor(author);
                }
                if (post.getPageProfile() != null && post.getPageProfile().getId() != null) {
                    PageProfile page = pageProfileRepository.getById(post.getPageProfile().getId(), dto.getToken());
                    post.setPageProfile(page);
                }
            }

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

            for (Event event : events) {
                if (event.getAuthor() != null && event.getAuthor().getId() != null) {
                    User author = userRepository.getById(event.getAuthor().getId(), dto.getToken());
                    event.setAuthor(author);
                }
                if (event.getPageProfile() != null && event.getPageProfile().getId() != null) {
                    PageProfile page = pageProfileRepository.getById(event.getPageProfile().getId(), dto.getToken());
                    event.setPageProfile(page);
                }
            }

        }

        return ResultsMapper.getSearchResult().toResponse(users, pageProfiles, posts, events);
    }

    @Override
    public GetFeedPageRes getFeedPage(GetFeedPageReq dto) {
        User user = this.userRepository.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        List<Post> posts = this.postRepository.getFilteredPosts(
            dto.getToken(),
            dto.getPage(),
            dto.getSize(),
            "",
            "",
            secretKeyHelper.getSecret()
        );

        for (Post post : posts) {
            if (post.getAuthor() != null && post.getAuthor().getId() != null) {
                User author = this.userRepository.getById(post.getAuthor().getId(), dto.getToken());
                post.setAuthor(author);
            }
            if (post.getPageProfile() != null && post.getPageProfile().getId() != null) {
                PageProfile page = this.pageProfileRepository.getById(post.getPageProfile().getId(), dto.getToken());
                post.setPageProfile(page);
            }
        }

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

        List<Object> feed = new ArrayList<>();

        feed.addAll(posts);
        feed.addAll(events);

        feed.sort(Comparator.comparing(FeedUtil::getCreatedAt).reversed());

        for (Object feedContent : feed) {

            if (feedContent instanceof Event event) {

                if (event.getAuthor() != null && event.getAuthor().getId() != null) {
                    User author = userRepository.getById(event.getAuthor().getId(), dto.getToken());
                    event.setAuthor(author);
                }
                if (event.getPageProfile() != null && event.getPageProfile().getId() != null) {
                    PageProfile page = pageProfileRepository.getById(event.getPageProfile().getId(), dto.getToken());
                    event.setPageProfile(page);
                }

            } else if (feedContent instanceof Post post) {

                if (post.getAuthor() != null && post.getAuthor().getId() != null) {
                    User author = this.userRepository.getById(post.getAuthor().getId(), dto.getToken());
                    post.setAuthor(author);
                }
                if (post.getPageProfile() != null && post.getPageProfile().getId() != null) {
                    PageProfile page = this.pageProfileRepository.getById(post.getPageProfile().getId(), dto.getToken());
                    post.setPageProfile(page);
                }

            }

        }

        return ResultsMapper.getFeedMerged().toResponse(feed);
    }

}
