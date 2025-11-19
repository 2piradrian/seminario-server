package com.group3.catalog.data.repository;

import com.group3.catalog.data.datasource.postgres.mapper.PostTypeEntityMapper;
import com.group3.catalog.data.datasource.postgres.model.PostTypeModel;
import com.group3.catalog.data.datasource.postgres.repository.PostgresPostTypeRepositoryI;
import com.group3.catalog.domain.repository.PostTypeRepositoryI;
import com.group3.entity.PostType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class PostTypeRepository implements PostTypeRepositoryI {

    private final PostgresPostTypeRepositoryI postTypeRepository;

    @Override
    public PostType getById(String postTypeId){
        PostTypeModel model = this.postTypeRepository.findById(postTypeId).orElse(null);
        return model != null ? PostTypeEntityMapper.toDomain(model) : null;
    }

    @Override
    public List<PostType> getAll() {
        List<PostTypeModel> models = this.postTypeRepository.findAll();
        return PostTypeEntityMapper.toDomain(models);
    }

    @Override
    public PostType save(PostType postType) {
        PostTypeModel model = PostTypeEntityMapper.toModel(postType);
        PostTypeModel saved = this.postTypeRepository.save(model);
        return PostTypeEntityMapper.toDomain(saved);
    }

    @Override
    public PostType update(PostType postType) {
        PostTypeModel model = PostTypeEntityMapper.toModel(postType);
        PostTypeModel updated = this.postTypeRepository.save(model);
        return PostTypeEntityMapper.toDomain(updated);
    }

}
