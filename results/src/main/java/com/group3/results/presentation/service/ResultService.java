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
        User user = this.userRepository.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        ContentType contentType = this.catalogRepository.getContentById(dto.getContentTypeId());
        if (contentType == null) throw new ErrorHandler(ErrorType.CONTENT_TYPE_NOT_FOUND);

        List<UserProfile> userProfiles = new ArrayList<>(List.of());
        List<Post> posts = new ArrayList<>(List.of());
        List<PageProfile> pageProfiles = new ArrayList<>(List.of());

        if (contentType.getName().equals("Páginas")){
            List<PageProfile> pageResponse =
                this.pageProfileRepository.getPageFilteredPage(
                    dto.getText(),
                    dto.getPageTypeId(),
                    dto.getPage(),
                    dto.getSize(),
                    this.secretKeyHelper.getSecret()
                );
            pageProfiles.addAll(pageResponse);
            return ResultsMapper.getSearchResult().toResponse(userProfiles, pageProfiles, posts);
        }

        if (contentType.getName().equals("Usuarios")){

            List<String> styleIds = new ArrayList<>();
            if (dto.getStyles() != null) styleIds = dto.getStyles().stream().map(Style::getId).toList();

            List<String> instrumentIds = new ArrayList<>();
            if (dto.getInstruments() != null) instrumentIds = dto.getInstruments().stream().map(Instrument::getId).toList();

            List<UserProfile> userResponse =
                this.userProfileRepository.getUserFilteredPage(
                    dto.getText(),
                    styleIds,
                    instrumentIds,
                    dto.getPage(),
                    dto.getSize(),
                    this.secretKeyHelper.getSecret()
                );

            userProfiles.addAll(userResponse);
            return ResultsMapper.getSearchResult().toResponse(userProfiles, pageProfiles, posts);
        }

        if (contentType.getName().equals("Posts")) {
            List<Post> postsResponse =
                this.postRepository.getFilteredPosts(
                    dto.getPage(),
                    dto.getSize(),
                    dto.getText(),
                    this.secretKeyHelper.getSecret()
                );

            for (Post post : postsResponse) {
                if (post.getAuthor().getId() != null) {
                    UserProfile fullProfile = this.userProfileRepository.getById(post.getAuthor().getId(), dto.getToken());
                    post.setAuthor(fullProfile);
                }
                if (post.getPageProfile().getId() != null) {
                    PageProfile fullPage = this.pageProfileRepository.getById(post.getPageProfile().getId());
                    post.setPageProfile(fullPage);
                }
            }

            posts.addAll(postsResponse);
            return ResultsMapper.getSearchResult().toResponse(userProfiles, pageProfiles, posts);
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
