package com.group3.users.data.datasource.catalog_server.responses.page_type;

import com.group3.entity.PageType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetPageTypeListByIdRes {

    private List<PageType> pageTypes;

}
