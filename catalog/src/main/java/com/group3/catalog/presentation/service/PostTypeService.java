package com.group3.catalog.presentation.service;

import com.group3.catalog.data.repository.PostTypeRepository;
import com.group3.catalog.domain.dto.posttype.mapper.PostTypeMapper;
import com.group3.catalog.data.repository.PostTypeRepository;
import com.group3.catalog.data.repository.UserRepository;
import com.group3.catalog.domain.dto.posttype.mapper.PostTypeMapper;
import com.group3.catalog.domain.dto.posttype.request.*;
import com.group3.catalog.domain.dto.posttype.response.*;
import com.group3.entity.PostType;
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
public class PostTypeService implements PostTypeServiceI {

    private final PostTypeRepository repository;
    private final UserRepository userRepository;

    @Override
    public GetAllPostTypeRes getAll(){
        List<PostType> postType = this.repository.getAll();
        return PostTypeMapper.getAll().toResponse(postType);
    }

    @Override
    public GetPostTypeByIdRes getById(GetPostTypeByIdReq dto) {
        PostType postType = this.repository.getById(dto.getId());

        if (postType == null) {
            throw new ErrorHandler(ErrorType.POSTYPE_NOT_FOUND);
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

    @Override
    public CreatePostTypeRes create(CreatePostTypeReq dto) {
        User user = this.userRepository.auth(dto.getToken());
        if (user == null || user.getRole() != Role.ADMIN) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        PostType postType = new PostType();
        postType.setName(dto.getName());

        PostType saved = this.repository.save(postType);

        return PostTypeMapper.create().toResponse(saved);
    }

    @Override
    public EditPostTypeRes edit(EditPostTypeReq dto) {
        User user = this.userRepository.auth(dto.getToken());
        if (user == null || user.getRole() != Role.ADMIN) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        PostType postType = this.repository.getById(dto.getId());

        if (postType == null) {
            throw new ErrorHandler(ErrorType.POSTYPE_NOT_FOUND);
        }

        postType.setName(dto.getName());

        PostType updated = this.repository.update(postType);

        return PostTypeMapper.edit().toResponse(updated);
    }

    @Override
    public void delete(DeletePostTypeReq dto) {
        User user = this.userRepository.auth(dto.getToken());
        if (user == null || user.getRole() != Role.ADMIN) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        PostType postType = this.repository.getById(dto.getId());

        if (postType == null) {
            throw new ErrorHandler(ErrorType.POSTYPE_NOT_FOUND);
        }

        this.repository.delete(postType.getId());
    }
}
