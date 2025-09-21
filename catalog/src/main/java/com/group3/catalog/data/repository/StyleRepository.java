package com.group3.catalog.data.repository;

import com.group3.catalog.data.postgres.mapper.StyleEntityMapper;
import com.group3.catalog.data.postgres.model.StyleModel;
import com.group3.catalog.data.postgres.repository.PostgresStyleRepositoryI;
import com.group3.catalog.domain.repository.StyleRepositoryI;
import com.group3.entity.Style;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class StyleRepository implements StyleRepositoryI {

    private final PostgresStyleRepositoryI styleRepository;

    @Override
    public Style getById(String styleId) {
        StyleModel model = this.styleRepository.findById(styleId).orElse(null);
        return model != null ? StyleEntityMapper.toDomain(model) : null;
    }

    @Override
    public List<Style> getAll() {
        List<StyleModel> models = this.styleRepository.findAll();
        return StyleEntityMapper.toDomain(models);
    }

    @Override
    public Style save(Style style) {
        StyleModel model = StyleEntityMapper.toModel(style);
        StyleModel saved = this.styleRepository.save(model);
        return StyleEntityMapper.toDomain(saved);
    }

    @Override
    public Style update(Style style) {
        StyleModel model = StyleEntityMapper.toModel(style);
        StyleModel updated = this.styleRepository.save(model);
        return StyleEntityMapper.toDomain(updated);
    }

}
