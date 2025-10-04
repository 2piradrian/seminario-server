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

import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class PostsRepository implements PostRepositoryI {

    private final PostgresPostRepositoryI repository;

    private int normalizePage(Integer page) {
        return (page != null && page > 0) ? page - 1 : 0;
    }

    @Override
    public Post getById(String postId) {
        PostModel postModel = this.repository.findById(postId).orElse(null);

        if (postModel == null) return null;
        if (postModel.getStatus().equals(Status.DELETED)) return null;

        return PostsEntityMapper.toDomain(postModel);
    }

    @Override
    public PageContent<Post> getAllPosts(Integer page, Integer size) {
        int pageIndex = normalizePage(page);

        Page<PostModel> postModels = this.repository.findAll(
                Status.DELETED,
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

    @Override
    public PageContent<Post> getPostsByUserId(String userId, Integer page, Integer size) {
        int pageIndex = normalizePage(page);

        Page<PostModel> postModels = repository.findByAuthorId(
                userId,
                Status.DELETED,
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

    @Override
    public PageContent<Post> getPostsByPageId(String pageId, Integer page, Integer size) {
        int pageIndex = normalizePage(page);

        Page<PostModel> postModels = repository.findByPageId(
                pageId,
                Status.DELETED,
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
