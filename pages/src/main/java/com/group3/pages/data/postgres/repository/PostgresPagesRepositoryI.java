package com.group3.pages.data.postgres.repository;

import com.group3.pages.data.postgres.model.PageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostgresPagesRepositoryI extends JpaRepository<PageModel, String> {
    
    Optional<PageModel> findByOwnerId(String ownerId);
    
    @Query("""
        SELECT p
        FROM PageModel p
        WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))
    """)
    List<PageModel> findByNameLike(@Param("name") String name);

}
