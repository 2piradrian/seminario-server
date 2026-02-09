package com.group3.catalog.domain.repository;

import com.group3.entity.ModerationReason;

import java.util.List;

public interface ModerationReasonRepositoryI {

    ModerationReason getById(String moderationReasonId);

    List<ModerationReason> getAll();

    ModerationReason save(ModerationReason moderationReason);

    ModerationReason update(ModerationReason moderationReason);

    void delete(String moderationReasonId);

}
