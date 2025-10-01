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

    @Override
    public Comment getById(String commentId) {
        CommentModel commentModel = this.repository.findById(commentId).orElse(null);

        if (commentModel == null) {
            return null;
        }

        if (commentModel.getStatus().equals(Status.DELETED)) {
            commentModel.setAuthorId(Status.DELETED.toString());
            commentModel.setContent(Status.DELETED.toString());

            return CommentEntityMapper.toDomain(commentModel);
        }

        return CommentEntityMapper.toDomain(commentModel);
    }

    @Override
    public PageContent<Comment> getByPostId(String postId, Integer page, Integer size) {
        Page<CommentModel> commentModels = this.repository.findAllByPostId(postId, PageRequest.of(page, size));

        return new PageContent<Comment>(
            commentModels.getContent().stream().map(CommentEntityMapper::toDomain).collect(Collectors.toList()),
            commentModels.getNumber(),
            commentModels.hasNext() ? commentModels.getNumber() + 1 : null
        );
    }

    @Override
    public Comment save(Comment comment) {
        CommentModel commentModel = CommentEntityMapper.toModel(comment);
        CommentModel saved = this.repository.save(commentModel);

        return CommentEntityMapper.toDomain(saved);
    }

    @Override
    public Comment update(Comment comment) {
        CommentModel commentModel = CommentEntityMapper.toModel(comment);
        CommentModel updated = this.repository.save(commentModel);

        return CommentEntityMapper.toDomain(updated);
    }

}
