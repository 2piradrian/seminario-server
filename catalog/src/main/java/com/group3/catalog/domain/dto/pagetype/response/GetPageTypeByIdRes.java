package com.group3.catalog.domain.dto.pagetype.response;

import com.group3.entity.PageType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetPageTypeByIdRes {

    private final PageType pageType;

}
