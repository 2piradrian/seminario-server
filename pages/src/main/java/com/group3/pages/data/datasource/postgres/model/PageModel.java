package com.group3.pages.data.datasource.postgres.model;

import com.group3.entity.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pages")
public class PageModel {

    @Id
    private String id;

    @Column(unique = true)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String portraitImage;

    @Column(columnDefinition = "TEXT")
    private String profileImage;

    private String shortDescription;

    private String longDescription;

    private String ownerId;

    @ElementCollection
    @CollectionTable(name = "page_members", joinColumns = @JoinColumn(name = "page_id"))
    @Column(name = "user_id")
    private List<String> members;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String pageTypeId;

}
