package com.group3.pages.domain.dto.response;

import com.group3.entity.Page;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetPageByUserIdRes {

    List<Page> pages;

}
