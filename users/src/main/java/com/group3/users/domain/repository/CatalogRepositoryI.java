package com.group3.users.domain.repository;

import com.group3.entity.*;

import java.util.List;


public interface CatalogRepositoryI {

    List<Style> getAllStyle();

    Style getStyleById(String styleId);

    List<Style> getStyleListById(List<String> styles);

    List<Instrument> getAllInstrument();

    Instrument getInstrumentById(String instrumentId);

    List<Instrument> getInstrumentListById(List<String> instruments);

    List<Category> getAllCategory();

    Category getCategoryById(String categoryId);

    List<Category> getCategoryListById(List<String> categories);

    List<ContentType> getAllContentType();

    ContentType getContentTypeById(String contentTypeId);

    List<ContentType> getContentTypeListById(List<String> contentTypes);

    List<ModerationReason> getAllModerationReason();

    ModerationReason getModerationReasonById(String moderationReasonId);

    List<PageType> getAllPageType();

    PageType getPageTypeById(String pageTypeId);

    List<PageType> getPageTypeListById(List<String> pageTypes);

    List<PostType> getAllPostType();

    PostType getPostTypeById(String postTypeId);

    List<PostType> getPostTypeListById(List<String> postTypes);

}
