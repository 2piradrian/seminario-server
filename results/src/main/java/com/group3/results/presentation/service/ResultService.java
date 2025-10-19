package com.group3.results.presentation.service;

import com.group3.entity.*;
import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import com.group3.results.config.helpers.SecretKeyHelper;
import com.group3.results.data.repository.*;
import com.group3.results.domain.dto.mapper.ResultsMapper;
import com.group3.results.domain.dto.request.GetFeedPageReq;
import com.group3.results.domain.dto.request.GetSerchResultFilteredReq;
import com.group3.results.domain.dto.response.GetFeedPageRes;
import com.group3.results.domain.dto.response.GetSearchResultFilteredRes;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ResultService implements ResultServiceI {

    private final SecretKeyHelper secretKeyHelper;

    private final UserProfileRepository userProfileRepository;

    private final PageProfileRepository pageProfileRepository;

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    private final CatalogRepository catalogRepository;

    @Override
    public GetSearchResultFilteredRes getSearchResult(GetSerchResultFilteredReq dto) {
        User user = userRepository.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        UserProfile profile = userProfileRepository.getByIdWithFollowing(user.getId(), secretKeyHelper.getSecret());
        if (profile == null) throw new ErrorHandler(ErrorType.USER_NOT_FOUND);

        ContentType contentType = catalogRepository.getContentById(dto.getContentTypeId());
        if (contentType == null) throw new ErrorHandler(ErrorType.CONTENT_TYPE_NOT_FOUND);

        String secret = secretKeyHelper.getSecret();
        List<UserProfile> userProfiles = new ArrayList<>();
        List<PageProfile> pageProfiles = new ArrayList<>();
        List<Post> posts = new ArrayList<>();

        switch (contentType.getName()) {

            case "Páginas" -> {
                pageProfiles = pageProfileRepository.getPageFilteredPage(
                        dto.getText(),
                        dto.getPageTypeId(),
                        dto.getPage(),
                        dto.getSize(),
                        secret
                );

                for (PageProfile page : pageProfiles) {
                    page.setIsFollowing(profile.getFollowing().contains(page.getId()));
                }
            }

            case "Usuarios" -> {
                List<String> styleIds = dto.getStyles() != null
                        ? dto.getStyles().stream().map(Style::getId).toList()
                        : List.of();

                List<String> instrumentIds = dto.getInstruments() != null
                        ? dto.getInstruments().stream().map(Instrument::getId).toList()
                        : List.of();

                userProfiles = userProfileRepository.getUserFilteredPage(
                        dto.getText(),
                        styleIds,
                        instrumentIds,
                        dto.getPage(),
                        dto.getSize(),
                        secret
                );

                for (UserProfile u : userProfiles) {
                    u.setIsFollowing(profile.getFollowing().contains(u.getId()));
                }
            }

            case "Posts" -> {
                posts = postRepository.getFilteredPosts(
                        dto.getPage(),
                        dto.getSize(),
                        dto.getText(),
                        secret
                );

                for (Post post : posts) {
                    if (post.getAuthor() != null && post.getAuthor().getId() != null) {
                        UserProfile author = userProfileRepository.getById(post.getAuthor().getId(), dto.getToken());
                        post.setAuthor(author);
                    }
                    if (post.getPageProfile() != null && post.getPageProfile().getId() != null) {
                        PageProfile page = pageProfileRepository.getById(post.getPageProfile().getId());
                        post.setPageProfile(page);
                    }
                }
            }
        }

        return ResultsMapper.getSearchResult().toResponse(userProfiles, pageProfiles, posts);
    }

    @Override
    public GetFeedPageRes getFeedPage(GetFeedPageReq dto) {
        User user = this.userRepository.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        UserProfile profile = this.userProfileRepository.getByIdWithFollowing(user.getId(), secretKeyHelper.getSecret());
        if (profile == null) throw new ErrorHandler(ErrorType.USER_NOT_FOUND);

        List<String> profiles = new ArrayList<>(List.of());

        profiles.addAll(profile.getFollowing());
        profiles.add(profile.getId());

        List<Post> posts = this.postRepository.getFilteredPosts(
            dto.getPage(),
            dto.getSize(),
            "",
            secretKeyHelper.getSecret()
        );

        return ResultsMapper.getFeed().toResponse(posts);
    }


}
