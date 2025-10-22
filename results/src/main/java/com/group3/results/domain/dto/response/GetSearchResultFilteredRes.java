package com.group3.results.domain.dto.response;

import com.group3.entity.PageProfile;
import com.group3.entity.Post;
import com.group3.entity.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetSearchResultFilteredRes {

    private final List<UserProfile> userProfiles;

    private final List<PageProfile> pageProfiles;

    private final List<Post> posts;

}
