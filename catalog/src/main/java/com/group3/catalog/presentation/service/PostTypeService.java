package com.group3.catalog.presentation.service;

import com.group3.catalog.data.repository.PostTypeRepository;
import com.group3.catalog.domain.dto.posttype.mapper.PostTypeMapper;
import com.group3.catalog.domain.dto.posttype.request.GetPostTypeByIdReq;
import com.group3.catalog.domain.dto.posttype.request.GetPostTypeListByIdReq;
import com.group3.catalog.domain.dto.posttype.response.GetAllPostTypeRes;
import com.group3.catalog.domain.dto.posttype.response.GetPostTypeByIdRes;
import com.group3.catalog.domain.dto.posttype.response.GetPostTypeListByIdRes;
import com.group3.entity.PostType;
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
public class PostTypeService implements PostTypeServiceI {

    private final PostTypeRepository repository;

    @Override
    public GetAllPostTypeRes getAll(){
        List<PostType> postType = this.repository.getAll();
        return PostTypeMapper.getAll().toResponse(postType);
    }

    @Override
    public GetPostTypeByIdRes getById(GetPostTypeByIdReq dto) {
        PostType postType = this.repository.getById(dto.getId());

        if (postType == null) {
            throw new ErrorHandler(ErrorType.STYLE_NOT_FOUND);
        }

        return PostTypeMapper.getById().toResponse(postType);
    }

    @Override
    public GetPostTypeListByIdRes getListById(GetPostTypeListByIdReq dto) {
        List<PostType> postTypes = dto.getIds().stream()
                .map(this.repository::getById)
                .filter(Objects::nonNull)
                .toList();

        if (postTypes.isEmpty()) {
            return PostTypeMapper.getListById().toResponse(List.of());
        }

        return PostTypeMapper.getListById().toResponse(postTypes);
    }
}
