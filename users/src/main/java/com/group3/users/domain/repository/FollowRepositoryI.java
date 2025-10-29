package com.group3.users.domain.repository;

import com.group3.entity.Follow;
import com.group3.entity.PageContent;

public interface FollowRepositoryI {

    Follow save(Follow follow);

    Follow update(Follow follow);

    void delete(String followId);

    PageContent<Follow> findByFollowerId(String followerId, Integer page, Integer size);

    PageContent<Follow> findByFollowedId(String followedId, Integer page, Integer size);
}
