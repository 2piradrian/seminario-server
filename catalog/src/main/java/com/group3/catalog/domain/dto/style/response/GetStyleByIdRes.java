package com.group3.catalog.domain.dto.style.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetStyleByIdRes {

    private final String id;

    private final String name;

}
