package com.group3.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Page {

    private String id;

    private String name;

    private String imageId;

    private String ownerId;

    private List<String> members;

    private Status status;

}
