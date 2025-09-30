package com.group3.posts.presentation.service;

import com.group3.posts.domain.dto.comment.request.*;
import com.group3.posts.domain.dto.comment.response.CreateCommentRes;
import com.group3.posts.domain.dto.comment.response.GetCommentPageRes;

public interface CommentServiceI {

    GetCommentPageRes getComments(GetCommentPageReq dto);

    CreateCommentRes create(CreateCommentReq dto);

    void toggleVotes(ToggleCommentVotesReq dto);

    void delete(DeleteCommentReq dto);

}
