package com.group3.posts.presentation.service;

import com.group3.posts.domain.dto.post.request.*;
import com.group3.posts.domain.dto.post.response.*;

public interface PostServiceI {

    CreatePostRes create(CreatePostReq dto);

    GetPostByIdRes getById(GetPostByIdReq dto);

    GetPostPageRes getPosts(GetPostPageReq dto);

    GetPostPageByProfileRes getPostsByProfile(GetPostPageByProfileReq dto);

    GetOwnPostPageRes getOwnPosts(GetOwnPostPageReq dto);

    TogglePostVotesRes toggleVotes(TogglePostVotesReq dto);

    EditPostRes edit(EditPostReq dto);

    void delete(DeletePostReq dto);

}
