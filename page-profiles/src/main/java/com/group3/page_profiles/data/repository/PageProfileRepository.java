package com.group3.page_profiles.data.repository;

import com.group3.entity.PageProfile;
import com.group3.page_profiles.data.datasource.postgres.mapper.PageEntityMapper;
import com.group3.page_profiles.data.datasource.postgres.model.PageProfileModel;
import com.group3.page_profiles.data.datasource.postgres.repository.PostgresPageProfileRepositoryI;
import com.group3.page_profiles.domain.repository.PageRepositoryI;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class PageProfileRepository implements PageRepositoryI {

    private final PostgresPageProfileRepositoryI repository;


    // ======== Single Page Retrieval ========

    @Override
    public PageProfile getById(String pageId) {
        PageProfileModel pageProfileModel = this.repository.findById(pageId).orElse(null);
        return pageProfileModel != null ? PageEntityMapper.toDomain(pageProfileModel) : null;
    }

    @Override
    public PageProfile getByName(String name) {
        PageProfileModel pageProfileModel = this.repository.findByName(name);
        return pageProfileModel != null ? PageEntityMapper.toDomain(pageProfileModel) : null;
    }


    // ======== Multiple Pages Retrieval ========

    @Override
    public List<PageProfile> getByUserId(String userId) {
        List<PageProfileModel> pageProfileModels = this.repository.findByUserId(userId);
        return pageProfileModels.isEmpty() ? List.of() : PageEntityMapper.toDomain(pageProfileModels);
    }

    @Override
    public List<PageProfile> getByNameLike(String name) {
        List<PageProfileModel> pageProfileModels = this.repository.findByNameLike(name);
        return pageProfileModels.isEmpty() ? List.of() : PageEntityMapper.toDomain(pageProfileModels);
    }

    @Override
    public List<PageProfile> getListByIds(List<String> ids) {
        List<PageProfileModel> pageProfileModels = this.repository.findAllByIdIn(ids);
        return pageProfileModels.isEmpty() ? List.of() : PageEntityMapper.toDomain(pageProfileModels);
    }


    // ======== Save and Update ========

    @Override
    public PageProfile save(PageProfile page) {
        PageProfileModel pageProfileModel = PageEntityMapper.toModel(page);
        PageProfileModel saved = this.repository.save(pageProfileModel);
        return PageEntityMapper.toDomain(saved);
    }

    @Override
    public PageProfile update(PageProfile page) {
        PageProfileModel pageProfileModel = PageEntityMapper.toModel(page);
        PageProfileModel updated = this.repository.save(pageProfileModel);
        return PageEntityMapper.toDomain(updated);
    }

}
