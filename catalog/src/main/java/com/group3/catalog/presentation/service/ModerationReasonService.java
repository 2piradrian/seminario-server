package com.group3.catalog.presentation.service;

import com.group3.catalog.data.repository.ModerationReasonRepository;
import com.group3.catalog.data.repository.UserRepository;
import com.group3.catalog.domain.dto.moderationReason.mapper.ModerationReasonMapper;
import com.group3.catalog.domain.dto.moderationReason.request.*;
import com.group3.catalog.domain.dto.moderationReason.response.*;
import com.group3.entity.ModerationReason;
import com.group3.entity.Role;
import com.group3.entity.User;
import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class ModerationReasonService implements ModerationReasonServiceI {

    private final ModerationReasonRepository repository;
    private final UserRepository userRepository;

    @Override
    public GetAllModerationReasonRes getAll(){
        List<ModerationReason> moderationReasons = this.repository.getAll();
        return ModerationReasonMapper.getAll().toResponse(moderationReasons);
    }

    @Override
    public GetModerationReasonByIdRes getById(GetModerationReasonByIdReq dto) {
        ModerationReason moderationReason = this.repository.getById(dto.getId());

        if (moderationReason == null) {
            throw new ErrorHandler(ErrorType.MODERATION_REASON_NOT_FOUND);
        }

        return ModerationReasonMapper.getById().toResponse(moderationReason);
    }

    @Override
    public CreateModerationReasonRes create(CreateModerationReasonReq dto) {
        User user = this.userRepository.auth(dto.getToken());
        if (user == null || user.getRole() != Role.ADMIN) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        ModerationReason moderationReason = new ModerationReason();
        moderationReason.setName(dto.getName());

        ModerationReason saved = this.repository.save(moderationReason);

        return ModerationReasonMapper.create().toResponse(saved);
    }

    @Override
    public EditModerationReasonRes edit(EditModerationReasonReq dto) {
        User user = this.userRepository.auth(dto.getToken());
        if (user == null || user.getRole() != Role.ADMIN) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        ModerationReason moderationReason = this.repository.getById(dto.getId());

        if (moderationReason == null) {
            throw new ErrorHandler(ErrorType.MODERATION_REASON_NOT_FOUND);
        }

        moderationReason.setName(dto.getName());

        ModerationReason updated = this.repository.update(moderationReason);

        return ModerationReasonMapper.edit().toResponse(updated);
    }

    @Override
    public void delete(DeleteModerationReasonReq dto) {
        User user = this.userRepository.auth(dto.getToken());
        if (user == null || user.getRole() != Role.ADMIN) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        ModerationReason moderationReason = this.repository.getById(dto.getId());

        if (moderationReason == null) {
            throw new ErrorHandler(ErrorType.MODERATION_REASON_NOT_FOUND);
        }

        this.repository.delete(moderationReason.getId());
    }
}
