package com.group3.pages.data.datasource.postgres.repository;

import com.group3.pages.data.datasource.postgres.model.PageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostgresPagesRepositoryI extends JpaRepository<PageModel, String> {
    
    @Query("""
        SELECT p
        FROM PageModel p
        WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))
    """)
    List<PageModel> findByNameLike(@Param("name") String name);

    @Query(value = "SELECT * FROM page_model WHERE :userId = ANY(members)", nativeQuery = true)
    List<PageModel> findByUserId(@Param("userId") String userId);

}
