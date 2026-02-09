package com.group3.catalog.data.repository;

import com.group3.catalog.data.datasource.postgres.mapper.PageTypeEntityMapper;
import com.group3.catalog.data.datasource.postgres.model.PageTypeModel;
import com.group3.catalog.data.datasource.postgres.repository.PostgresPageTypeRepositoryI;
import com.group3.catalog.domain.repository.PageTypeRepositoryI;
import com.group3.entity.PageType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class PageTypeRepository implements PageTypeRepositoryI {

    private final PostgresPageTypeRepositoryI pageTypeRepository;

    @Override
    public PageType getById(String pageTypeId){
        PageTypeModel model = this.pageTypeRepository.findById(pageTypeId).orElse(null);
        return model != null ? PageTypeEntityMapper.toDomain(model) : null;
    }

    @Override
    public List<PageType> getAll() {
        List<PageTypeModel> models = this.pageTypeRepository.findAll();
        return PageTypeEntityMapper.toDomain(models);
    }

    @Override
    public PageType save(PageType pageType) {
        PageTypeModel model = PageTypeEntityMapper.toModel(pageType);
        PageTypeModel saved = this.pageTypeRepository.save(model);
        return PageTypeEntityMapper.toDomain(saved);
    }

    @Override
    public PageType update(PageType pageType) {
        PageTypeModel model = PageTypeEntityMapper.toModel(pageType);
        PageTypeModel updated = this.pageTypeRepository.save(model);
        return PageTypeEntityMapper.toDomain(updated);
    }

    @Override
    public void delete(String pageTypeId) {
        this.pageTypeRepository.deleteById(pageTypeId);
    }

}
