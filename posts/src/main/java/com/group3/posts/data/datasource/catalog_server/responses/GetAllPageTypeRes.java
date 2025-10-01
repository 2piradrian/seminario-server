package com.group3.posts.data.datasource.catalog_server.responses;

import com.group3.entity.PageType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetAllPageTypeRes {

    List<PageType> pageTypes;

}
