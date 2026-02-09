package com.group3.users.data.datasource.catalog_server.repository;

import com.group3.users.config.beans.LoadBalancerConfiguration;
import com.group3.users.data.datasource.catalog_server.responses.category.*;
import com.group3.users.data.datasource.catalog_server.responses.content_type.*;
import com.group3.users.data.datasource.catalog_server.responses.instrument.*;
import com.group3.users.data.datasource.catalog_server.responses.moderation_reason.*;
import com.group3.users.data.datasource.catalog_server.responses.page_type.*;
import com.group3.users.data.datasource.catalog_server.responses.post_type.*;
import com.group3.users.data.datasource.catalog_server.responses.style.*;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(name = "catalog-server", path = "/catalog-server")
@LoadBalancerClient(name = "catalog-server", configuration = LoadBalancerConfiguration.class)
public interface CatalogServerRepositoryI {

    // Styles
    @GetMapping("/api/styles/get-all")
    GetAllStyleRes getAllStyle();

    @GetMapping("/api/styles/get-by-id/{styleId}")
    GetStyleByIdRes getStyleById(
            @PathVariable("styleId") String styleId
    );

    @GetMapping("/api/styles/get-list-by-id")
    GetStyleListByIdRes getStyleListById(
            @RequestParam(value = "ids") List<String> ids
    );

    @PostMapping("/api/styles")
    CreateStyleRes createStyle(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody Map<String, Object> payload
    );

    @PutMapping("/api/styles/{styleId}")
    EditStyleRes editStyle(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable(value = "styleId") String styleId,
            @RequestBody Map<String, Object> payload
    );

    @DeleteMapping("/api/styles/{styleId}")
    void deleteStyle(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable(value = "styleId") String styleId
    );

    // Instruments
    @GetMapping("/api/instruments/get-all")
    GetAllInstrumentRes getAllInstrument();

    @GetMapping("/api/instruments/get-by-id/{instrumentId}")
    GetInstrumentByIdRes getInstrumentById(
            @PathVariable("instrumentId") String instrumentId
    );

    @GetMapping("/api/instruments/get-list-by-id")
    GetInstrumentListByIdRes getInstrumentListById(
            @RequestParam(value = "ids") List<String> ids
    );

    @PostMapping("/api/instruments")
    CreateInstrumentRes createInstrument(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody Map<String, Object> payload
    );

    @PutMapping("/api/instruments/{instrumentId}")
    EditInstrumentRes editInstrument(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable(value = "instrumentId") String instrumentId,
            @RequestBody Map<String, Object> payload
    );

    @DeleteMapping("/api/instruments/{instrumentId}")
    void deleteInstrument(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable(value = "instrumentId") String instrumentId
    );

    // Categories
    @GetMapping("/api/categories/get-all")
    GetAllCategoryRes getAllCategory();

    @GetMapping("/api/categories/get-by-id/{categoryId}")
    GetCategoryByIdRes getCategoryById(
            @PathVariable("categoryId") String categoryId
    );

    @GetMapping("/api/categories/get-list-by-id")
    GetCategoryListByIdRes getCategoryListById(
            @RequestParam(value = "ids") List<String> ids
    );

    @PostMapping("/api/categories")
    CreateCategoryRes createCategory(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody Map<String, Object> payload
    );

    @PutMapping("/api/categories/{categoryId}")
    EditCategoryRes editCategory(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable(value = "categoryId") String categoryId,
            @RequestBody Map<String, Object> payload
    );

    @DeleteMapping("/api/categories/{categoryId}")
    void deleteCategory(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable(value = "categoryId") String categoryId
    );

    // Content Types
    @GetMapping("/api/content-types/get-all")
    GetAllContentTypeRes getAllContentType();

    @GetMapping("/api/content-types/get-by-id/{contentTypeId}")
    GetContentTypeByIdRes getContentTypeById(
            @PathVariable("contentTypeId") String contentTypeId
    );

    @GetMapping("/api/content-types/get-list-by-id")
    GetContentTypeListByIdRes getContentTypeListById(
            @RequestParam(value = "ids") List<String> ids
    );

    @PostMapping("/api/content-types")
    CreateContentTypeRes createContentType(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody Map<String, Object> payload
    );

    @PutMapping("/api/content-types/{contentTypeId}")
    EditContentTypeRes editContentType(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable(value = "contentTypeId") String contentTypeId,
            @RequestBody Map<String, Object> payload
    );

    @DeleteMapping("/api/content-types/{contentTypeId}")
    void deleteContentType(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable(value = "contentTypeId") String contentTypeId
    );

    // Moderation Reasons
    @GetMapping("/api/moderation-reasons/get-all")
    GetAllModerationReasonRes getAllModerationReason();

    @GetMapping("/api/moderation-reasons/get-by-id/{moderationReasonId}")
    GetModerationReasonByIdRes getModerationReasonById(
            @PathVariable("moderationReasonId") String moderationReasonId
    );

    @PostMapping("/api/moderation-reasons")
    CreateModerationReasonRes createModerationReason(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody Map<String, Object> payload
    );

    @PutMapping("/api/moderation-reasons/{moderationReasonId}")
    EditModerationReasonRes editModerationReason(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable(value = "moderationReasonId") String moderationReasonId,
            @RequestBody Map<String, Object> payload
    );

    @DeleteMapping("/api/moderation-reasons/{moderationReasonId}")
    void deleteModerationReason(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable(value = "moderationReasonId") String moderationReasonId
    );

    // Page Types
    @GetMapping("/api/page-types/get-all")
    GetAllPageTypeRes getAllPageType();

    @GetMapping("/api/page-types/get-by-id/{pageTypeId}")
    GetPageTypeByIdRes getPageTypeById(
            @PathVariable("pageTypeId") String pageTypeId
    );

    @GetMapping("/api/page-types/get-list-by-id")
    GetPageTypeListByIdRes getPageTypeListById(
            @RequestParam(value = "ids") List<String> ids
    );

    @PostMapping("/api/page-types")
    CreatePageTypeRes createPageType(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody Map<String, Object> payload
    );

    @PutMapping("/api/page-types/{pageTypeId}")
    EditPageTypeRes editPageType(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable(value = "pageTypeId") String pageTypeId,
            @RequestBody Map<String, Object> payload
    );

    @DeleteMapping("/api/page-types/{pageTypeId}")
    void deletePageType(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable(value = "pageTypeId") String pageTypeId
    );

    // Post Types
    @GetMapping("/api/post-types/get-all")
    GetAllPostTypeRes getAllPostType();

    @GetMapping("/api/post-types/get-by-id/{postTypeId}")
    GetPostTypeByIdRes getPostTypeById(
            @PathVariable("postTypeId") String postTypeId
    );

    @PostMapping("/api/post-types/get-list-by-id")
    GetPostTypeListByIdRes getPostTypeListById(
            @RequestBody Map<String, Object> payload
    );

    @PostMapping("/api/post-types")
    CreatePostTypeRes createPostType(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody Map<String, Object> payload
    );

    @PutMapping("/api/post-types/{postTypeId}")
    EditPostTypeRes editPostType(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable(value = "postTypeId") String postTypeId,
            @RequestBody Map<String, Object> payload
    );

    @DeleteMapping("/api/post-types/{postTypeId}")
    void deletePostType(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable(value = "postTypeId") String postTypeId
    );

}
