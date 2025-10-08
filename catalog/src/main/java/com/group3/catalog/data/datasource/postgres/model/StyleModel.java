package com.group3.catalog.data.datasource.postgres.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "styles")
public class StyleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

}
