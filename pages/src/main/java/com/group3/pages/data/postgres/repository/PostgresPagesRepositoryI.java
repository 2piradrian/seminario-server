package com.group3.pages.data.postgres.repository;

import com.group3.pages.data.postgres.model.PageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostgresPagesRepositoryI extends JpaRepository<PageModel, String> {
    
    @Query("""
        SELECT p
        FROM PageModel p
        WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))
    """)
    List<PageModel> findByNameLike(@Param("name") String name);

    @Query("""
    SELECT p
    FROM PageModel p
    WHERE p.ownerId = :userId OR :userId MEMBER OF p.members
    """)
    List<PageModel> findByUserId(@Param("userId") String userId);

}
