package com.group3.pages.data.postgres.model;

import com.group3.entity.PageType;
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
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String portraitImage;

    @Column(columnDefinition = "TEXT")
    private String profileImage;

    private String shortDescription;

    private String longDescription;

    private String ownerId;

    private List<String> members;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private PageType pageType;

}
