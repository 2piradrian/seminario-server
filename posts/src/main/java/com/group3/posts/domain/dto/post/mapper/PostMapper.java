package com.group3.posts.domain.dto.post.mapper;

import com.group3.posts.domain.dto.post.mapper.implementation.*;

public class PostMapper {

    public static CreateMapper create() {
        return new CreateMapper();
    }

    public static GetPageMapper getPage() {
        return new GetPageMapper();
    }

    public static GetOwnPageMapper getOwnPage() {
        return new GetOwnPageMapper();
    }

    public static GetPageByProfileMapper getPageByProfile() {
        return new GetPageByProfileMapper();
    }

    public static DeleteMapper delete() {
        return new DeleteMapper();
    }

    public static EditMapper edit() {
        return new EditMapper();
    }

    public static GetByIdMapper getById() {
        return new GetByIdMapper();
    }

    public static ToggleVotesMapper toggleVotes() {
        return new ToggleVotesMapper();
    }

}
