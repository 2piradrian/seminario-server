package com.group3.results.domain.dto.mapper.implementation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group3.entity.Instrument;
import com.group3.entity.PageContent;
import com.group3.entity.Style;
import com.group3.results.domain.dto.request.GetProfilesFilteredReq;
import com.group3.results.domain.dto.response.GetProfilesFilteredRes;

import java.util.List;
import java.util.Map;

public class GetProfilesMapper {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public GetProfilesFilteredReq toRequest(Map<String, Object> payload) {
        return GetProfilesFilteredReq.create(
            (Integer) payload.get("page"),
            (Integer) payload.get("size"),
            (String) payload.get("name"),
            objectMapper.convertValue(payload.get("styles"), new TypeReference<List<Style>>() {}),
            objectMapper.convertValue(payload.get("instruments"), new TypeReference<List<Instrument>>() {}),
            objectMapper.convertValue(payload.get("ids"), new TypeReference<List<String>>() {}),
            (String) payload.get("pageTypeId")
        );
    }

    public GetProfilesFilteredRes toResponse(PageContent<String> profilesPage, List<Object> profiles) {
        return new GetProfilesFilteredRes(
            profiles,
            profilesPage.getNextPage()
        );
    }

}
