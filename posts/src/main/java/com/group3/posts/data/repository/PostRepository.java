package com.group3.posts.data.repository;

import com.group3.entity.Category;
import com.group3.entity.PageContent;
import com.group3.entity.Post;
import com.group3.entity.Status;
import com.group3.posts.data.postgres.mapper.PostsEntityMapper;
import com.group3.posts.data.postgres.model.PostModel;
import com.group3.posts.data.postgres.repository.PostgresPostRepositoryI;
import com.group3.posts.domain.repository.PostRepositoryI;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class PostRepository implements PostRepositoryI {

    private final PostgresPostRepositoryI postRepository;

    @Override
    public Post getById(String postId) {
        PostModel postModel = this.postRepository.findById(postId).orElse(null);

        if (postModel == null) return null;

        if (postModel.getStatus().equals(Status.DELETED)) return null;

        return PostsEntityMapper.toDomain(postModel);

    }

    @Override
    public PageContent<Post> getAllPosts(Integer page, Integer size) {
        Page<PostModel> postModels  = this.postRepository.findAll(Status.DELETED, PageRequest.of(page, size));

        return new PageContent<Post>(
                postModels.getContent().stream().map(PostsEntityMapper::toDomain).collect(Collectors.toList()),
                postModels.getNumber(),
                postModels.hasNext() ? postModels.getNumber() + 1 : null
        );

    }

    @Override
    public Post save(Post post) {
        PostModel postModel = PostsEntityMapper.toModel(post);
        PostModel saved = this.postRepository.save(postModel);

        return PostsEntityMapper.toDomain(saved);
    }

    @Override
    public Post update(Post post) {
        PostModel postModel = PostsEntityMapper.toModel(post);
        PostModel updated = this.postRepository.save(postModel);

        return PostsEntityMapper.toDomain(updated);
    }
}
