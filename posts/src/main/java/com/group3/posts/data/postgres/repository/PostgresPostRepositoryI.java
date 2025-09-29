package com.group3.posts.data.postgres.repository;

import com.group3.entity.Category;
import com.group3.entity.Status;
import com.group3.posts.data.postgres.model.PostModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PostgresPostRepositoryI extends JpaRepository<PostModel, String> {

    @Query(
            value = "SELECT p FROM PostModel p WHERE p.status <> :status ORDER BY p.createdAt DESC"
    )
    Page<PostModel> findAll(
            @Param("status") Status status,
            Pageable pageable
    );

    @Query(
            value = "SELECT p from PostModel p WHERE p.category = :category " +
                    "AND p.status <> :status ORDER BY p.createdAt DESC"
    )
    Page<PostModel> findAllByCategory(
            @Param("category") Category category,
            @Param("status") Status status,
            Pageable pageable
    );

    @Query(
            value = "SELECT p FROM PostModel p WHERE p.createdAt BETWEEN :starDate AND :endDate " +
                    "AND p.status <> :status ORDER BY p.createdAt DESC"
    )
    List<PostModel> getMonthlyPosts(
            @Param("starDate") LocalDateTime startDate,
            @Param("endDate")LocalDateTime endDate,
            @Param("status") Status status
    );
}
