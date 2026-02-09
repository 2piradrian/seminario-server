package com.group3.catalog.presentation.service;

import com.group3.catalog.data.repository.PageTypeRepository;
import com.group3.catalog.domain.dto.pagetype.mapper.PageTypeMapper;
import com.group3.catalog.data.repository.PageTypeRepository;
import com.group3.catalog.data.repository.UserRepository;
import com.group3.catalog.domain.dto.pagetype.mapper.PageTypeMapper;
import com.group3.catalog.domain.dto.pagetype.request.*;
import com.group3.catalog.domain.dto.pagetype.response.*;
import com.group3.entity.PageType;
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
public class PageTypeService implements PageTypeServiceI {

    private final PageTypeRepository repository;
    private final UserRepository userRepository;

    @Override
    public GetAllPageTypeRes getAll(){
        List<PageType> pageType = this.repository.getAll();
        return PageTypeMapper.getAll().toResponse(pageType);
    }

    @Override
    public GetPageTypeByIdRes getById(GetPageTypeByIdReq dto) {
        PageType pageType = this.repository.getById(dto.getId());

        if (pageType == null) {
            throw new ErrorHandler(ErrorType.PAGETYPE_NOT_FOUND);
        }

        return PageTypeMapper.getById().toResponse(pageType);
    }

    @Override
    public GetPageTypeListByIdRes getListById(GetPageTypeListByIdReq dto) {
        List<PageType> pageTypes = dto.getIds().stream()
                .map(this.repository::getById)
                .filter(Objects::nonNull)
                .toList();

        if (pageTypes.isEmpty()) {
            return PageTypeMapper.getListById().toResponse(List.of());
        }

        return PageTypeMapper.getListById().toResponse(pageTypes);
    }

    @Override
    public CreatePageTypeRes create(CreatePageTypeReq dto) {
        User user = this.userRepository.auth(dto.getToken());
        if (user == null || user.getRole() != Role.ADMIN) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        PageType pageType = new PageType();
        pageType.setName(dto.getName());

        PageType saved = this.repository.save(pageType);

        return PageTypeMapper.create().toResponse(saved);
    }

    @Override
    public EditPageTypeRes edit(EditPageTypeReq dto) {
        User user = this.userRepository.auth(dto.getToken());
        if (user == null || user.getRole() != Role.ADMIN) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        PageType pageType = this.repository.getById(dto.getId());

        if (pageType == null) {
            throw new ErrorHandler(ErrorType.PAGETYPE_NOT_FOUND);
        }

        pageType.setName(dto.getName());

        PageType updated = this.repository.update(pageType);

        return PageTypeMapper.edit().toResponse(updated);
    }

    @Override
    public void delete(DeletePageTypeReq dto) {
        User user = this.userRepository.auth(dto.getToken());
        if (user == null || user.getRole() != Role.ADMIN) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        PageType pageType = this.repository.getById(dto.getId());

        if (pageType == null) {
            throw new ErrorHandler(ErrorType.PAGETYPE_NOT_FOUND);
        }

        this.repository.delete(pageType.getId());
    }
}
