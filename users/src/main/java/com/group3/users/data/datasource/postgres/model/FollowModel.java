package com.group3.users.data.datasource.postgres.model;

import com.group3.entity.UserProfile;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "follows")
public class FollowModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "followed_id", nullable = false)
    private String followedId;

    @Column(name = "follower_id", nullable = false)
    private String followerId;

}
