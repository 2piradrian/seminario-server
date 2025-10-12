package com.group3.page_profiles.data.datasource.postgres.repository;

import com.group3.page_profiles.data.datasource.postgres.model.PageProfileModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostgresPageProfileRepositoryI extends JpaRepository<PageProfileModel, String> {

    PageProfileModel findByName(String name);
    
    @Query("""
        SELECT p
        FROM PageProfileModel p
        WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))
    """)
    List<PageProfileModel> findByNameLike(@Param("name") String name);

    @Query("""
        SELECT p
        FROM PageProfileModel p
        JOIN p.members m
        WHERE m = :userId
    """)
    List<PageProfileModel> findByUserId(@Param("userId") String userId);

    List<PageProfileModel> findAllByIdIn(List<String> ids);

}
