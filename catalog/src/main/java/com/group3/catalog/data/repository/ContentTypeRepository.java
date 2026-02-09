package com.group3.catalog.data.repository;

import com.group3.catalog.data.datasource.postgres.mapper.ContentTypeEntityMapper;
import com.group3.catalog.data.datasource.postgres.model.ContentTypeModel;
import com.group3.catalog.data.datasource.postgres.repository.PostgresContentTypeRepositoryI;
import com.group3.catalog.domain.repository.ContentTypeRepositoryI;
import com.group3.entity.ContentType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class ContentTypeRepository implements ContentTypeRepositoryI {

    private final PostgresContentTypeRepositoryI contentTypeRepository;

    @Override
    public ContentType getById(String contentTypeId) {
        ContentTypeModel model = this.contentTypeRepository.findById(contentTypeId).orElse(null);
        return model != null ? ContentTypeEntityMapper.toDomain(model) : null;
    }

    @Override
    public List<ContentType> getAll() {
        List<ContentTypeModel> models = this.contentTypeRepository.findAll();
        return ContentTypeEntityMapper.toDomain(models);
    }

    @Override
    public ContentType save(ContentType contentType) {
        ContentTypeModel model = ContentTypeEntityMapper.toModel(contentType);
        ContentTypeModel saved = this.contentTypeRepository.save(model);
        return ContentTypeEntityMapper.toDomain(saved);
    }

    @Override
    public ContentType update(ContentType contentType) {
        ContentTypeModel model = ContentTypeEntityMapper.toModel(contentType);
        ContentTypeModel updated = this.contentTypeRepository.save(model);
        return ContentTypeEntityMapper.toDomain(updated);
    }

    @Override
    public void delete(String contentTypeId) {
        this.contentTypeRepository.deleteById(contentTypeId);
    }

}
