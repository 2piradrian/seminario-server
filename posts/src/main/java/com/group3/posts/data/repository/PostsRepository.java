package com.group3.posts.data.repository;

import com.group3.entity.PageContent;
import com.group3.entity.Post;
import com.group3.entity.Status;
import com.group3.posts.data.datasource.postgres.mapper.PostsEntityMapper;
import com.group3.posts.data.datasource.postgres.model.PostModel;
import com.group3.posts.data.datasource.postgres.repository.PostgresPostRepositoryI;
import com.group3.posts.domain.repository.PostRepositoryI;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
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
        if (!postModel.getStatus().equals(Status.ACTIVE)) return null;

        return PostsEntityMapper.toDomain(postModel);
    }


    // ======== Get All Posts with Pagination ========

    @Override
    public PageContent<Post> getAllPosts(Integer page, Integer size) {
        int pageIndex = normalizePage(page);

        Page<PostModel> postModels = this.repository.findAll(
                Status.ACTIVE,
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


    // ======== Get Posts by User ID with Pagination ========

    @Override
    public PageContent<Post> getPostsByUserId(String userId, Integer page, Integer size) {
        int pageIndex = normalizePage(page);

        Page<PostModel> postModels = repository.findByAuthorId(
                userId,
                Status.ACTIVE,
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


    // ======== Get Posts by Page ID with Pagination ========

    @Override
    public PageContent<Post> getPostsByPageId(String pageId, Integer page, Integer size) {
        int pageIndex = normalizePage(page);

        Page<PostModel> postModels = repository.findByPageId(
                pageId,
                Status.ACTIVE,
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
    public PageContent<Post> getFilteredPosts(List<String> ids, Integer page, Integer size, String text) {
        int pageIndex = normalizePage(page);

        Page<PostModel> postModels = repository.findByFilteredPage(
                ids,
                Status.ACTIVE,
                text,
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

}
