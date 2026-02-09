package com.group3.catalog.presentation.service;

import com.group3.catalog.data.repository.StyleRepository;
import com.group3.catalog.domain.dto.style.mapper.StyleMapper;
import com.group3.catalog.data.repository.StyleRepository;
import com.group3.catalog.data.repository.UserRepository;
import com.group3.catalog.domain.dto.style.mapper.StyleMapper;
import com.group3.catalog.domain.dto.style.request.*;
import com.group3.catalog.domain.dto.style.response.*;
import com.group3.entity.Role;
import com.group3.entity.Style;
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
public class StyleService implements StyleServiceI {

    private final StyleRepository repository;
    private final UserRepository userRepository;

    @Override
    public GetAllStyleRes getAll() {
        List<Style> styles = this.repository.getAll();
        return StyleMapper.getAll().toResponse(styles);
    }

    @Override
    public GetStyleByIdRes getById(GetStyleByIdReq dto) {
        Style style = this.repository.getById(dto.getId());

        if (style == null) {
            throw new ErrorHandler(ErrorType.STYLE_NOT_FOUND);
        }

        return StyleMapper.getById().toResponse(style);
    }

    @Override
    public GetStyleListByIdRes getListById(GetStyleListByIdReq dto) {
        List<Style> styles = dto.getIds().stream()
            .map(this.repository::getById)
            .filter(Objects::nonNull)
            .toList();

        if (styles.isEmpty()) {
            return StyleMapper.getListById().toResponse(List.of());
        }

        return StyleMapper.getListById().toResponse(styles);
    }

    @Override
    public CreateStyleRes create(CreateStyleReq dto) {
        User user = this.userRepository.auth(dto.getToken());
        if (user == null || user.getRole() != Role.ADMIN) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        Style style = new Style();
        style.setName(dto.getName());

        Style saved = this.repository.save(style);

        return StyleMapper.create().toResponse(saved);
    }

    @Override
    public EditStyleRes edit(EditStyleReq dto) {
        User user = this.userRepository.auth(dto.getToken());
        if (user == null || user.getRole() != Role.ADMIN) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        Style style = this.repository.getById(dto.getId());

        if (style == null) {
            throw new ErrorHandler(ErrorType.STYLE_NOT_FOUND);
        }

        style.setName(dto.getName());

        Style updated = this.repository.update(style);

        return StyleMapper.edit().toResponse(updated);
    }

    @Override
    public void delete(DeleteStyleReq dto) {
        User user = this.userRepository.auth(dto.getToken());
        if (user == null || user.getRole() != Role.ADMIN) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        Style style = this.repository.getById(dto.getId());

        if (style == null) {
            throw new ErrorHandler(ErrorType.STYLE_NOT_FOUND);
        }

        this.repository.delete(style.getId());
    }

}
