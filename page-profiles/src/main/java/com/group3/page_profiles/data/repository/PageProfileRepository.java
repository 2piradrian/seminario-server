package com.group3.page_profiles.data.repository;

import com.group3.entity.PageContent;
import com.group3.entity.PageProfile;
import com.group3.entity.Status;
import com.group3.page_profiles.data.datasource.postgres.mapper.PageEntityMapper;
import com.group3.page_profiles.data.datasource.postgres.model.PageProfileModel;
import com.group3.page_profiles.data.datasource.postgres.repository.PostgresPageProfileRepositoryI;
import com.group3.page_profiles.domain.repository.PageRepositoryI;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Repository
@AllArgsConstructor
public class PageProfileRepository implements PageRepositoryI {

    private final PostgresPageProfileRepositoryI repository;

    // ======== Pagination Helper ========

    private int normalizePage(Integer page) {
        return (page != null && page > 0) ? page - 1 : 0;
    }


    // ======== Single Page Retrieval ========

    @Override
    public PageProfile getById(String pageId) {
        PageProfileModel pageProfileModel = this.repository.findById(pageId).orElse(null);

        if (pageProfileModel == null) return null;

        return PageEntityMapper.toDomain(pageProfileModel);
    }

    @Override
    public PageProfile getByName(String name) {
        PageProfileModel pageProfileModel = this.repository.findByName(name);
        if (pageProfileModel == null) return null;

        return PageEntityMapper.toDomain(pageProfileModel);
    }


    // ======== Multiple Pages Retrieval ========

    @Override
    public List<PageProfile> getByUserId(String userId) {
        List<PageProfileModel> pageProfileModels = this.repository.findByUserId(userId);
        return pageProfileModels.isEmpty() ? List.of() : PageEntityMapper.toDomain(pageProfileModels);
    }

    @Override
    public PageContent<PageProfile> getFilteredPage(String name, String pageTypeId, Integer page, Integer size) {
        int pageIndex = normalizePage(page);

        Page<PageProfileModel> pageProfileModels = repository.findByFilteredPage(
            name,
            pageTypeId,
            PageRequest.of(pageIndex, size)
        );

        return new PageContent<>(
            pageProfileModels.getContent().stream()
                .map(PageEntityMapper::toDomain)
                .collect(Collectors.toList()),

            pageProfileModels.getNumber() + 1,

            pageProfileModels.hasNext() ? pageProfileModels.getNumber() + 2 : null
        );
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

    // ======== Delete Operations ========

    @Override
    public void delete(String pageId) {
        this.repository.deleteById(pageId);
    }

    @Override
    public void deleteByOwnerId(String ownerId) {
        this.repository.deleteByOwnerId(ownerId);
    }

    @Override
    public void removeMemberFromAllPages(String userId) {
        this.repository.removeMemberFromAllPages(userId);
    }

}