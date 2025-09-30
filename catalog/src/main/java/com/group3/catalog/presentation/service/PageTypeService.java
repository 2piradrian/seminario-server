package com.group3.catalog.presentation.service;

import com.group3.catalog.data.repository.PageTypeRepository;
import com.group3.catalog.domain.dto.pagetype.mapper.PageTypeMapper;
import com.group3.catalog.domain.dto.pagetype.request.GetPageTypeByIdReq;
import com.group3.catalog.domain.dto.pagetype.request.GetPageTypeListByIdReq;
import com.group3.catalog.domain.dto.pagetype.response.GetAllPageTypeRes;
import com.group3.catalog.domain.dto.pagetype.response.GetPageTypeByIdRes;
import com.group3.catalog.domain.dto.pagetype.response.GetPageTypeListByIdRes;
import com.group3.entity.PageType;
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

    @Override
    public GetAllPageTypeRes getAll(){
        List<PageType> pageType = this.repository.getAll();
        return PageTypeMapper.getAll().toResponse(pageType);
    }

    @Override
    public GetPageTypeByIdRes getById(GetPageTypeByIdReq dto) {
        PageType pageType = this.repository.getById(dto.getId());

        if (pageType == null) {
            throw new ErrorHandler(ErrorType.STYLE_NOT_FOUND);
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
}
