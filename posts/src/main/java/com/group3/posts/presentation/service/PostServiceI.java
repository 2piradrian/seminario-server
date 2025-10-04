package com.group3.posts.presentation.service;

import com.group3.posts.domain.dto.post.request.*;
import com.group3.posts.domain.dto.post.response.*;

public interface PostServiceI {

    GetPostByIdRes getById(GetPostByIdReq dto);

    GetPostPageRes getPosts(GetPostPageReq dto);

    GetPostPageByProfileRes getPostsByProfile(GetPostPageByProfileReq dto);

    CreatePostRes create(CreatePostReq dto);

    EditPostRes edit(EditPostReq dto);

    void  toggleVotes(TogglePostVotesReq dto);

    void delete(DeletePostReq dto);

}
