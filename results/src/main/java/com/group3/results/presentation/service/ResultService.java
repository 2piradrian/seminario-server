package com.group3.results.presentation.service;

import com.group3.entity.*;
import com.group3.results.config.helpers.SecretKeyHelper;
import com.group3.results.data.repository.PageProfileRepository;
import com.group3.results.data.repository.PostRepository;
import com.group3.results.data.repository.UserProfileRepository;
import com.group3.results.domain.dto.mapper.ResultsMapper;
import com.group3.results.domain.dto.request.GetFeedPageReq;
import com.group3.results.domain.dto.request.GetProfilesFilteredReq;
import com.group3.results.domain.dto.response.GetFeedPageRes;
import com.group3.results.domain.dto.response.GetProfilesFilteredRes;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ResultService implements ResultServiceI {

    private final SecretKeyHelper secretKeyHelper;

    private final UserProfileRepository userProfileRepository;

    private final PageProfileRepository pageProfileRepository;

    private final PostRepository postRepository;


    @Override
    public GetProfilesFilteredRes getProfilesFiltered(GetProfilesFilteredReq dto) {

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

        return ResultsMapper.getProfilesFiltered().toResponse(userProfiles, pageProfiles, posts);
    }

    @Override
    public GetFeedPageRes getFeedPage(GetFeedPageReq dto) {

        User user = new User();



        return null;
    }


}
