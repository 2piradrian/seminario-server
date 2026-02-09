package com.group3.posts.domain.dto.comment.mapper;

import com.group3.posts.domain.dto.comment.mapper.implementation.*;

public class CommentMapper {

    public static GetPageMapper getPage() {return new GetPageMapper();}

    public static CreateMapper create() {
        return new CreateMapper();
    }

    public static DeleteMapper delete() {
        return new DeleteMapper();
    }

    public static ToggleVotesMapper toggleVotes() {
        return new ToggleVotesMapper();
    }

    public static GetByIdMapper getById() {
        return new GetByIdMapper();
    }

    public static DeleteCommentsByUserIdMapper deleteCommentsByUserId() {
        return new DeleteCommentsByUserIdMapper();
    }

    public static DeleteCommentsByPageIdMapper deleteCommentsByPageId() {
        return new DeleteCommentsByPageIdMapper();
    }
}
