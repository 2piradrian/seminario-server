package com.group3.users.domain.dto.review.response;

import com.group3.entity.Role;
import com.group3.entity.Status;
import com.group3.entity.UserProfile;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class GetReviewByIdRes {

    private final String id;

    private final String reviewedId;

    private final String reviewerUserId;

    private final String review;

    private final Float rating;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;

    private final Status status;

}
