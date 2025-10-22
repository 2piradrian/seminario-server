package com.group3.posts.data.repository;

import com.group3.entity.Comment;
import com.group3.entity.PageContent;
import com.group3.entity.Status;
import com.group3.posts.data.datasource.postgres.mapper.CommentEntityMapper;
import com.group3.posts.data.datasource.postgres.model.CommentModel;
import com.group3.posts.data.datasource.postgres.repository.PostgresCommentRepositoryI;
import com.group3.posts.domain.repository.CommentRepositoryI;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class CommentRepository implements CommentRepositoryI {

    private final PostgresCommentRepositoryI repository;


    // ======== Pagination Helper ========

    private int normalizePage(Integer page) {
        return (page != null && page > 0) ? page - 1 : 0;
    }


    // ======== Single Comment Retrieval ========

    @Override
    public Comment getById(String commentId) {
        CommentModel commentModel = this.repository.findById(commentId).orElse(null);

        if (commentModel != null && !commentModel.getStatus().equals(Status.ACTIVE)) {
            return null;
        }

        if (commentModel == null) return null;
        return CommentEntityMapper.toDomain(commentModel);
    }


    // ======== Get Comments by Post ID with Pagination ========

    @Override
    public PageContent<Comment> getByPostId(String postId, Integer page, Integer size) {
        int pageIndex = normalizePage(page);

        Page<CommentModel> commentModels = this.repository.findAllByPostIdAndActiveStatus(
                postId,
                Status.ACTIVE,
                PageRequest.of(pageIndex, size)
        );

        return new PageContent<>(
                commentModels.getContent().stream()
                        .map(CommentEntityMapper::toDomain)
                        .collect(Collectors.toList()),
                commentModels.getNumber() + 1,
                commentModels.hasNext() ? commentModels.getNumber() + 2 : null
        );
    }


    // ======== Save Comment ========

    @Override
    public Comment save(Comment comment) {
        CommentModel commentModel = CommentEntityMapper.toModel(comment);
        CommentModel saved = this.repository.save(commentModel);
        return CommentEntityMapper.toDomain(saved);
    }


    // ======== Update Comment ========

    @Override
    public Comment update(Comment comment) {
        CommentModel commentModel = CommentEntityMapper.toModel(comment);
        CommentModel updated = this.repository.save(commentModel);
        return CommentEntityMapper.toDomain(updated);
    }

}
