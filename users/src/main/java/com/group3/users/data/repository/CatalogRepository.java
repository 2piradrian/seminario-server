package com.group3.users.data.repository;

import com.group3.entity.*;
import com.group3.users.data.datasource.catalog_server.repository.CatalogServerRepositoryI;
import com.group3.users.domain.repository.CatalogRepositoryI;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class CatalogRepository implements CatalogRepositoryI {

    private final CatalogServerRepositoryI repository;


    // ======== Styles ========

    @Override
    public List<Style> getAllStyle() {
        List<Style> styles = this.repository.getAllStyle().getStyles();
        return styles == null ? List.of() : styles;
    }

    @Override
    public Style getStyleById(String styleId) {
        return this.repository.getStyleById(styleId).getStyle();
    }

    @Override
    public List<Style> getStyleListById(List<String> styles) {
        return this.repository.getStyleListById(styles).getStyles();
    }


    // ======== Instruments ========

    @Override
    public List<Instrument> getAllInstrument() {
        List<Instrument> instruments = this.repository.getAllInstrument().getInstruments();
        return instruments != null ? instruments : List.of();
    }

    @Override
    public Instrument getInstrumentById(String instrumentId) {
        return this.repository.getInstrumentById(instrumentId).getInstrument();
    }

    @Override
    public List<Instrument> getInstrumentListById(List<String> instruments) {
        return this.repository.getInstrumentListById(instruments).getInstruments();
    }

    // ======== Categories ========

    @Override
    public List<Category> getAllCategory() {
        List<Category> categories = this.repository.getAllCategory().getCategories();
        return categories != null ? categories : List.of();
    }

    @Override
    public Category getCategoryById(String categoryId) {
        return this.repository.getCategoryById(categoryId).getCategory();
    }

    @Override
    public List<Category> getCategoryListById(List<String> categories) {
        return this.repository.getCategoryListById(categories).getCategories();
    }

    // ======== Content Types ========

    @Override
    public List<ContentType> getAllContentType() {
        List<ContentType> contentTypes = this.repository.getAllContentType().getContentTypes();
        return contentTypes != null ? contentTypes : List.of();
    }

    @Override
    public ContentType getContentTypeById(String contentTypeId) {
        return this.repository.getContentTypeById(contentTypeId).getContentType();
    }

    @Override
    public List<ContentType> getContentTypeListById(List<String> contentTypes) {
        return this.repository.getContentTypeListById(contentTypes).getContentTypes();
    }

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

    // ======== Page Types ========

    @Override
    public List<PageType> getAllPageType() {
        List<PageType> pageTypes = this.repository.getAllPageType().getPageTypes();
        return pageTypes != null ? pageTypes : List.of();
    }

    @Override
    public PageType getPageTypeById(String pageTypeId) {
        return this.repository.getPageTypeById(pageTypeId).getPageType();
    }

    @Override
    public List<PageType> getPageTypeListById(List<String> pageTypes) {
        return this.repository.getPageTypeListById(pageTypes).getPageTypes();
    }

    // ======== Post Types ========

    @Override
    public List<PostType> getAllPostType() {
        List<PostType> postTypes = this.repository.getAllPostType().getPostTypes();
        return postTypes != null ? postTypes : List.of();
    }

    @Override
    public PostType getPostTypeById(String postTypeId) {
        return this.repository.getPostTypeById(postTypeId).getPostType();
    }

    @Override
    public List<PostType> getPostTypeListById(List<String> postTypes) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("ids", postTypes);
        return this.repository.getPostTypeListById(payload).getPostTypes();
    }

}
