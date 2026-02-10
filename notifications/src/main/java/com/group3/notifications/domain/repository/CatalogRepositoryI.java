package com.group3.notifications.domain.repository;

import com.group3.entity.*;

import java.util.List;


public interface CatalogRepositoryI {

    List<ModerationReason> getAllModerationReason();

    ModerationReason getModerationReasonById(String moderationReasonId);

}