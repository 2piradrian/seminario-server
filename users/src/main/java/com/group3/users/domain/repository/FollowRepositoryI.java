package com.group3.users.domain.repository;

import com.group3.entity.Follow;
import com.group3.entity.PageContent;

import java.util.List;

public interface FollowRepositoryI {

    Follow save(Follow follow);

    Follow update(Follow follow);

    void delete(String followId);

    PageContent<Follow> findByFollowerId(String followerId, Integer page, Integer size);

    PageContent<Follow> findByFollowedId(String followedId, Integer page, Integer size);

    List<Follow> getAllFollowers(String followedId);

    List<Follow> getAllFollowing(String followerId);

    Integer getFollowersQuantity(String followedId);

    Integer getFollowingQuantity(String followerId);

    void deleteByFollowerId(String followerId);

    void deleteByFollowedId(String followedId);

}
