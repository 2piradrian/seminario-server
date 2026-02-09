package com.group3.catalog.presentation.service;

import com.group3.catalog.data.repository.ContentTypeRepository;
import com.group3.catalog.domain.dto.contentType.mapper.ContentTypeMapper;
import com.group3.catalog.data.repository.ContentTypeRepository;
import com.group3.catalog.data.repository.UserRepository;
import com.group3.catalog.domain.dto.contentType.mapper.ContentTypeMapper;
import com.group3.catalog.domain.dto.contentType.request.*;
import com.group3.catalog.domain.dto.contentType.response.*;
import com.group3.entity.ContentType;
import com.group3.entity.Role;
import com.group3.entity.User;
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

    private final UserRepository userRepository;

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

    @Override
    public CreateContentTypeRes create(CreateContentTypeReq dto) {
        User user = this.userRepository.auth(dto.getToken());
        if (user == null || user.getRole() != Role.ADMIN) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        ContentType contentType = new ContentType();
        contentType.setName(dto.getName());

        ContentType saved = this.repository.save(contentType);

        return ContentTypeMapper.create().toResponse(saved);
    }

    @Override
    public EditContentTypeRes edit(EditContentTypeReq dto) {
        User user = this.userRepository.auth(dto.getToken());
        if (user == null || user.getRole() != Role.ADMIN) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        ContentType contentType = this.repository.getById(dto.getId());

        if (contentType == null) {
            throw new ErrorHandler(ErrorType.CONTENT_TYPE_NOT_FOUND);
        }

        contentType.setName(dto.getName());

        ContentType updated = this.repository.update(contentType);

        return ContentTypeMapper.edit().toResponse(updated);
    }

    @Override
    public void delete(DeleteContentTypeReq dto) {
        User user = this.userRepository.auth(dto.getToken());
        if (user == null || user.getRole() != Role.ADMIN) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        ContentType contentType = this.repository.getById(dto.getId());

        if (contentType == null) {
            throw new ErrorHandler(ErrorType.CONTENT_TYPE_NOT_FOUND);
        }

        this.repository.delete(contentType.getId());
    }

}
