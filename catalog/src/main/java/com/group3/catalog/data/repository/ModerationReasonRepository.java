package com.group3.catalog.data.repository;

import com.group3.catalog.data.datasource.postgres.mapper.ModerationReasonEntityMapper;
import com.group3.catalog.data.datasource.postgres.model.ModerationReasonModel;
import com.group3.catalog.data.datasource.postgres.repository.PostgresModerationReasonRepositoryI;
import com.group3.catalog.domain.repository.ModerationReasonRepositoryI;
import com.group3.entity.ModerationReason;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class ModerationReasonRepository implements ModerationReasonRepositoryI {

    private final PostgresModerationReasonRepositoryI moderationReasonRepository;

    @Override
    public ModerationReason getById(String moderationReasonId){
        ModerationReasonModel model = this.moderationReasonRepository.findById(moderationReasonId).orElse(null);
        return model != null ? ModerationReasonEntityMapper.toDomain(model) : null;
    }

    @Override
    public List<ModerationReason> getAll() {
        List<ModerationReasonModel> models = this.moderationReasonRepository.findAll();
        return ModerationReasonEntityMapper.toDomain(models);
    }

    @Override
    public ModerationReason save(ModerationReason moderationReason) {
        ModerationReasonModel model = ModerationReasonEntityMapper.toModel(moderationReason);
        ModerationReasonModel saved = this.moderationReasonRepository.save(model);
        return ModerationReasonEntityMapper.toDomain(saved);
    }

    @Override
    public ModerationReason update(ModerationReason moderationReason) {
        ModerationReasonModel model = ModerationReasonEntityMapper.toModel(moderationReason);
        ModerationReasonModel updated = this.moderationReasonRepository.save(model);
        return ModerationReasonEntityMapper.toDomain(updated);
    }

    @Override
    public void delete(String moderationReasonId) {
        this.moderationReasonRepository.deleteById(moderationReasonId);
    }

}
