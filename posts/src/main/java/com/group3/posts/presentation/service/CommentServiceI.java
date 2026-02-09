package com.group3.posts.presentation.service;

import com.group3.posts.domain.dto.comment.request.*;
import com.group3.posts.domain.dto.comment.response.CreateCommentRes;
import com.group3.posts.domain.dto.comment.response.GetCommentPageRes;
import com.group3.posts.domain.dto.comment.response.ToggleCommentVotesRes;


import com.group3.posts.domain.dto.comment.request.GetCommentByIdReq;
import com.group3.posts.domain.dto.comment.response.GetCommentByIdRes;

public interface CommentServiceI {

    CreateCommentRes create(CreateCommentReq dto);

    GetCommentByIdRes getById(GetCommentByIdReq dto);

    GetCommentPageRes getComments(GetCommentPageReq dto);

    ToggleCommentVotesRes toggleVotes(ToggleCommentVotesReq dto);

    void delete(DeleteCommentReq dto);

    void deleteCommentsByUserId(DeleteCommentsByUserIdReq dto);

    void deleteCommentsByPageId(DeleteCommentsByPageIdReq dto);

}
