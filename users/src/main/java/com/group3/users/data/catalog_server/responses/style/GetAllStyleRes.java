package com.group3.users.data.catalog_server.responses.style;

import com.group3.entity.Style;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetAllStyleRes {

    List<Style> styles;

}
