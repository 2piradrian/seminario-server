package com.group3.pages.data.catalog_server.responses;

import com.group3.entity.Instrument;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetAllPageTypeRes {

    List<Instrument> instruments;

}
