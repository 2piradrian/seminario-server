package com.group3.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    private String id;

    private UserProfile reviewedUser;

    private UserProfile reviewerUser;

    private String review;

    private Float rating;

}
