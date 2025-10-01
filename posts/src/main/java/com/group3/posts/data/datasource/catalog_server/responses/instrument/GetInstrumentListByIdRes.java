package com.group3.posts.data.datasource.catalog_server.responses.instrument;

import com.group3.entity.Instrument;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetInstrumentListByIdRes {

    List<Instrument> instruments;

}
