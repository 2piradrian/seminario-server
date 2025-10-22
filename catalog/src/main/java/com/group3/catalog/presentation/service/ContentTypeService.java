package com.group3.catalog.presentation.service;

import com.group3.catalog.data.repository.ContentTypeRepository;
import com.group3.catalog.domain.dto.contentType.mapper.ContentTypeMapper;
import com.group3.catalog.domain.dto.contentType.request.GetContentTypeByIdReq;
import com.group3.catalog.domain.dto.contentType.request.GetContentTypeListByIdReq;
import com.group3.catalog.domain.dto.contentType.response.GetAllContentTypeRes;
import com.group3.catalog.domain.dto.contentType.response.GetContentTypeByIdRes;
import com.group3.catalog.domain.dto.contentType.response.GetContentTypeListByIdRes;
import com.group3.entity.ContentType;
import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class ContentTypeService implements ContentTypeServiceI {

    private final ContentTypeRepository repository;

    @Override
    public GetAllContentTypeRes getAll() {
        List<ContentType> contentTypes = this.repository.getAll();
        return ContentTypeMapper.getAll().toResponse(contentTypes);
    }

    @Override
    public GetContentTypeByIdRes getById(GetContentTypeByIdReq dto) {
        ContentType contentType = this.repository.getById(dto.getId());

        if (contentType == null) {
            throw new ErrorHandler(ErrorType.CONTENT_TYPE_NOT_FOUND);
        }

        return ContentTypeMapper.getById().toResponse(contentType);
    }

    @Override
    public GetContentTypeListByIdRes getListById(GetContentTypeListByIdReq dto) {
        List<ContentType> contentTypes = dto.getIds().stream()
            .map(this.repository::getById)
            .filter(Objects::nonNull)
            .toList();

        if (contentTypes.isEmpty()) {
            return ContentTypeMapper.getListById().toResponse(List.of());
        }

        return ContentTypeMapper.getListById().toResponse(contentTypes);
    }

}
