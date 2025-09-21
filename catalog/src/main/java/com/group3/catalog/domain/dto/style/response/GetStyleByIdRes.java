package com.group3.catalog.domain.dto.style.response;

import com.group3.entity.Style;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetStyleByIdRes {

    private final Style style;

}
