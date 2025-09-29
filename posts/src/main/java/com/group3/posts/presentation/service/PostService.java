package com.group3.posts.presentation.service;

import com.group3.posts.domain.dto.post.request.*;
import com.group3.posts.domain.dto.post.response.*;

public interface PostService {

    GetPostByIdRes getById(GetPostByIdReq dto);

    GetPostPageRes getPosts(GetPostPageReq dto);

    CreatePostRes create(CreatePostReq dto);

    EditPostRes edit(EditPostReq dto);

    void  toggleVotes(TogglePostVotesReq dto);

    void delete(DeletePostReq dto);
}
