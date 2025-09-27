package com.group3.profiles.data.feign.catalog_server.responses.style;

import com.group3.entity.Style;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetStyleByIdRes {

    private final Style style;

}
