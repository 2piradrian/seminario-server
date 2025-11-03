package com.group3.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {

    private String id;

    private String name;

    private String surname;

    private LocalDateTime memberSince;

    private String portraitImage;

    private String profileImage;

    private String shortDescription;

    private String longDescription;

    private List<Style> styles;

    private List<Instrument> instruments;

    // Domain variables
    private Boolean isFollowing;
    private Boolean isOwnProfile;
    private Integer followingQuantity;
    private Integer followersQuantity;

    public void isOwnProfile(String profileId){
        this.isOwnProfile = this.id.equals(profileId);
    }

    public void setFollowsChecks(String profileId, List<String> followingList, List<String> followersList){
        this.followingQuantity = followingList.size();
        this.followersQuantity = followersList.size();
        this.isFollowing = followingList.contains(profileId);
    }

}
