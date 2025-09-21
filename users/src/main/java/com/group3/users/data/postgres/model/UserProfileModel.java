package com.group3.users.data.postgres.model;

import com.group3.entity.Instrument;
import com.group3.entity.Style;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user-profiles")
public class UserProfileModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String portraitImage;

    private String profileImage;

    private String shortDescription;

    private String longDescription;

    private Set<String> styles;

    private Set<String> instruments;

}
