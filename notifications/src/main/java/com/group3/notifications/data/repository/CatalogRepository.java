package com.group3.notifications.data.repository;

import com.group3.entity.*;
import com.group3.notifications.data.datasource.catalog_server.repository.CatalogServerRepositoryI;
import com.group3.notifications.domain.repository.CatalogRepositoryI;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class CatalogRepository implements CatalogRepositoryI {

    private final CatalogServerRepositoryI repository;

    // ======== Moderation Reasons ========

    @Override
    public List<ModerationReason> getAllModerationReason() {
        List<ModerationReason> moderationReasons = this.repository.getAllModerationReason().getModerationReasons();
        return moderationReasons != null ? moderationReasons : List.of();
    }

    @Override
    public ModerationReason getModerationReasonById(String moderationReasonId) {
        return this.repository.getModerationReasonById(moderationReasonId).getModerationReason();
    }

}
