package com.group3.users.data.repository;

import com.group3.entity.PageContent;
import com.group3.entity.Review;
import com.group3.users.data.datasource.postgres.mapper.ReviewEntityMapper;
import com.group3.users.data.datasource.postgres.model.ReviewModel;
import com.group3.users.data.datasource.postgres.repository.PostgresReviewRepositoryI;
import com.group3.users.domain.repository.ReviewRepositoryI;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class ReviewRepository implements ReviewRepositoryI {

    private final PostgresReviewRepositoryI repository;

    // ======== Helper Methods ========

    private int normalizePage(Integer page) {
        return (page != null && page > 0) ? page - 1 : 0;
    }

    // ======== CRUD Operations ========

    @Override
    public Review getById(String reviewId) {
        ReviewModel reviewModel = this.repository.findById(reviewId).orElse(null);
        return reviewModel != null ? ReviewEntityMapper.toDomain(reviewModel) : null;
    }

    @Override
    public Review save(Review review) {
        ReviewModel reviewModel = ReviewEntityMapper.toModel(review);
        ReviewModel saved = this.repository.save(reviewModel);
        return ReviewEntityMapper.toDomain(saved);
    }

    @Override
    public Review update(Review review) {
        ReviewModel reviewModel = ReviewEntityMapper.toModel(review);
        ReviewModel updated = this.repository.save(reviewModel);
        return ReviewEntityMapper.toDomain(updated);
    }

    @Override
    public void delete(String reviewId) {
        this.repository.deleteById(reviewId);
    }

    @Override
    public void deleteByReviewerId(String reviewerId) {
        this.repository.deleteByReviewerId(reviewerId);
    }

    @Override
    public void deleteByReviewedId(String reviewedId) {
        this.repository.deleteByReviewedId(reviewedId);
    }

    // ======== Finder Methods ========

    @Override
    public PageContent<Review> findByReviewerId(String reviewerId, Integer page, Integer size) {
        int pageIndex = this.normalizePage(page);

        Page<ReviewModel> reviewModels = repository.findByReviewerId(
                reviewerId,
                PageRequest.of(pageIndex, size)
        );

        return new PageContent<>(
                reviewModels.getContent().stream()
                        .map(ReviewEntityMapper::toDomain)
                        .collect(Collectors.toList()),
                reviewModels.getNumber() + 1,
                reviewModels.hasNext() ? reviewModels.getNumber() + 2 : null
        );
    }

    @Override
    public PageContent<Review> findByReviewedUserId(String reviewedUserId, Integer page, Integer size) {
        int pageIndex = this.normalizePage(page);

        Page<ReviewModel> reviewModels = repository.findByReviewedUserId(
                reviewedUserId,
                PageRequest.of(pageIndex, size)
        );

        return new PageContent<>(
                reviewModels.getContent().stream()
                        .map(ReviewEntityMapper::toDomain)
                        .collect(Collectors.toList()),
                reviewModels.getNumber() + 1,
                reviewModels.hasNext() ? reviewModels.getNumber() + 2 : null
        );
    }

}
