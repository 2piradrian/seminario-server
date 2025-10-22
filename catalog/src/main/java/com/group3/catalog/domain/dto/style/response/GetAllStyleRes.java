package com.group3.catalog.domain.dto.style.response;

import com.group3.entity.Style;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetAllStyleRes {

    private final List<Style> styles;

}
