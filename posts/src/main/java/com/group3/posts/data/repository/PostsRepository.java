package com.group3.posts.data.repository;

import com.group3.entity.*;
import com.group3.posts.data.datasource.postgres.mapper.PostsEntityMapper;
import com.group3.posts.data.datasource.postgres.model.PostModel;
import com.group3.posts.data.datasource.postgres.repository.PostgresPostRepositoryI;
import com.group3.posts.domain.repository.PostRepositoryI;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class PostsRepository implements PostRepositoryI {

    private final PostgresPostRepositoryI repository;

    // ======== Pagination Helper ========

    private int normalizePage(Integer page) {
        return (page != null && page > 0) ? page - 1 : 0;
    }


    // ======== Single Post Retrieval ========

    @Override
    public Post getById(String postId) {
        PostModel postModel = this.repository.findById(postId).orElse(null);

        if (postModel == null) return null;

        return PostsEntityMapper.toDomain(postModel);
    }


    // ======== Get All Posts with Pagination ========

    @Override
    public PageContent<Post> getAllPosts(Integer page, Integer size) {
        int pageIndex = normalizePage(page);

        Page<PostModel> postModels = this.repository.findAll(
                PageRequest.of(pageIndex, size, Sort.by(Sort.Direction.DESC, "createdAt"))
        );

        return new PageContent<>(
                postModels.getContent().stream()
                        .map(PostsEntityMapper::toDomain)
                        .collect(Collectors.toList()),
                postModels.getNumber() + 1,
                postModels.hasNext() ? postModels.getNumber() + 2 : null
        );
    }

    @Override
    public PageContent<Post> getOnlyPagePosts(Integer page, Integer size) {
        int pageIndex = normalizePage(page);

        Page<PostModel> postModels = this.repository.findOnlyPagePosts(
            PageRequest.of(pageIndex, size)
        );

        return new PageContent<>(
            postModels.getContent().stream()
                .map(PostsEntityMapper::toDomain)
                .collect(Collectors.toList()),
            postModels.getNumber() + 1,
            postModels.hasNext() ? postModels.getNumber() + 2 : null
        );
    }

    // ======== Get Posts by profile ID with Pagination ========

    @Override
    public PageContent<Post> getByProfileIdPage(String profileId, Integer page, Integer size){
        int pageIndex = normalizePage(page);

        Page<PostModel> postModels = this.repository.findByProfileIdPage(
            profileId,
            PageRequest.of(pageIndex, size)
        );

        return new PageContent<>(
            postModels.getContent().stream()
                .map(PostsEntityMapper::toDomain)
                .collect(Collectors.toList()),
            postModels.getNumber() + 1,
            postModels.hasNext() ? postModels.getNumber() + 2 : null
        );
    }


    // ======== Get Posts by Filtered Page or Author with Pagination ========

    @Override
    public PageContent<Post> getFilteredPosts(Integer page, Integer size, String text, String postTypeId) {
        int pageIndex = normalizePage(page);

        Page<PostModel> postModels = repository.findByFilteredPage(
                text,
                postTypeId,
                PageRequest.of(pageIndex, size)
        );

        return new PageContent<>(
                postModels.getContent().stream()
                        .map(PostsEntityMapper::toDomain)
                        .collect(Collectors.toList()),
                postModels.getNumber() + 1,
                postModels.hasNext() ? postModels.getNumber() + 2 : null
        );
    }


    // ======== Save and Update ========

    @Override
    public Post save(Post post) {
        PostModel postModel = PostsEntityMapper.toModel(post);
        PostModel saved = this.repository.save(postModel);

        return PostsEntityMapper.toDomain(saved);
    }

    @Override
    public Post update(Post post) {
        PostModel postModel = PostsEntityMapper.toModel(post);
        PostModel updated = this.repository.save(postModel);

        return PostsEntityMapper.toDomain(updated);
    }

    @Override
    public void deleteById(String id) {
        this.repository.deleteById(id);
    }

    @Override
    public void deleteAllUpvoters(String postId) {
        this.repository.deleteAllUpvoters(postId);
    }

    @Override
    public void deleteAllDownvoters(String postId) {
        this.repository.deleteAllDownvoters(postId);
    }

    @Override
    public void deleteUpvotesByUserId(String userId) {
        this.repository.deleteUpvotesByUserId(userId);
    }

    @Override
    public void deleteDownvotesByUserId(String userId) {
        this.repository.deleteDownvotesByUserId(userId);
    }

    @Override
    public void deleteAllByAuthorId(String authorId) {
        this.repository.deleteAllByAuthorId(authorId);
    }

    @Override
    public void deleteAllByPageId(String pageId) {
        this.repository.deleteAllByPageId(pageId);
    }
}
