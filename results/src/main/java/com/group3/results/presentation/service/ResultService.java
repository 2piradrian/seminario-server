package com.group3.results.presentation.service;

import com.group3.entity.*;
import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import com.group3.results.config.helpers.SecretKeyHelper;
import com.group3.results.data.repository.PageProfileRepository;
import com.group3.results.data.repository.PostRepository;
import com.group3.results.data.repository.UserProfileRepository;
import com.group3.results.data.repository.UserRepository;
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


    @Override
    public GetSearchResultFilteredRes getSearchResult(GetSerchResultFilteredReq dto) {

        User user = this.userRepository.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        List<UserProfile> userProfiles =
            this.userProfileRepository.getUserFilteredPage(
                dto.getName(),
                dto.getStyles().stream().map(Style::getId).toList(),
                dto.getInstruments().stream().map(Instrument::getId).toList(),
                dto.getIds(),
                dto.getPage(),
                dto.getSize(),
                this.secretKeyHelper.getSecret()
                );

        List<PageProfile> pageProfiles =
            this.pageProfileRepository.getPageFilteredPage(
                dto.getName(),
                dto.getPageTypeId(),
                dto.getIds(),
                dto.getPage(),
                dto.getSize(),
                this.secretKeyHelper.getSecret()
            );

        List<Post> posts =
            this.postRepository.getFilteredPosts(
                dto.getIds(),
                dto.getPage(),
                dto.getSize(),
                dto.getName(),
                this.secretKeyHelper.getSecret()
            );

        for (Post post : posts) {
            if (post.getAuthor().getId() != null) {
                UserProfile fullProfile = this.userProfileRepository.getById(post.getAuthor().getId(), dto.getToken());
                post.setAuthor(fullProfile);
            }
            if (post.getPageProfile().getId() != null) {
                PageProfile fullPage = this.pageProfileRepository.getById(post.getPageProfile().getId());
                post.setPageProfile(fullPage);
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
            profiles.stream().toList(),
            dto.getPage(),
            dto.getSize(),
            "",
            secretKeyHelper.getSecret()
        );

        return ResultsMapper.getFeed().toResponse(posts);
    }


}
