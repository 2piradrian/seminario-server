package com.group3.pages.data.repository;

import com.group3.entity.Page;
import com.group3.pages.data.postgres.mapper.PageEntityMapper;
import com.group3.pages.data.postgres.model.PageModel;
import com.group3.pages.data.postgres.repository.PostgresPagesRepositoryI;
import com.group3.pages.domain.repository.PageRepositoryI;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class PageRepository implements PageRepositoryI {

    private final PostgresPagesRepositoryI repository;

    @Override
    public Page getById(String pageId) {
        PageModel pageModel = this.repository.findById(pageId).orElse(null);
        return pageModel != null ? PageEntityMapper.toDomain(pageModel) : null;
    }

    @Override
    public List<Page> findByParticipantUserId(String userId) {
        List<PageModel> pageModels = this.repository.findByOwnerOrMember(userId);
        return pageModels.isEmpty() ? PageEntityMapper.toDomain(pageModels) : List.of();
    }

    @Override
    public List<Page> findByName(String name) {
        List<PageModel> pageModels = this.repository.findByNameLike(name);
        return pageModels.isEmpty() ? PageEntityMapper.toDomain(pageModels) : List.of();
    }

    @Override
    public Page save(Page page) {
        PageModel pageModel = PageEntityMapper.toModel(page);
        PageModel saved = this.repository.save(pageModel);

        return PageEntityMapper.toDomain(saved);
    }

    @Override
    public Page update(Page page) {
        PageModel pageModel = PageEntityMapper.toModel(page);
        PageModel updated = this.repository.save(pageModel);

        return PageEntityMapper.toDomain(updated);
    }
}
