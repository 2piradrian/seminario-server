package com.group3.users.data.datasource.postgres.repository;

import com.group3.entity.Role;
import com.group3.entity.Status;
import com.group3.users.data.datasource.postgres.model.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostgresUserRepositoryI extends JpaRepository<UserModel, String> {

    Optional<UserModel> findByEmail(String email);

    @Query("""
        SELECT u FROM UserModel u 
        WHERE u.role <> :roleExcluded
        AND u.status = :activeStatus
    """)
    List<UserModel> findWithExcludedRole(
        @Param("roleExcluded") Role roleExcluded,
        @Param("activeStatus") Status activeStatus
    );

    // ======== Get filtered By Filtered Page ========

    @Query("""
        SELECT u FROM UserModel u WHERE
        u.status = :status 
        AND
        (
            :#{#fullName == null or #fullName.isEmpty()} = true 
            OR 
            cast(function('unaccent', LOWER(CONCAT(u.profile.name, ' ', u.profile.surname))) as string) 
            LIKE LOWER(CONCAT('%', :fullName, '%'))
        ) 
        AND 
        (
            :#{#styles == null or #styles.isEmpty()} = true 
            OR EXISTS (SELECT 1 FROM u.profile.styles s WHERE s IN :styles)
        ) 
        AND 
        (
            :#{#instruments == null or #instruments.isEmpty()} = true 
            OR EXISTS (SELECT 1 FROM u.profile.instruments i WHERE i IN :instruments)
        )
    """)
    Page<UserModel> findByFilteredPage(
        @Param("fullName") String fullName,
        @Param("status") Status status,
        @Param("styles") List<String> styles,
        @Param("instruments") List<String> instruments,
        Pageable pageable
    );

    @Query(value = """
        WITH RECURSIVE user_graph AS (
        
            SELECT 
                f1.followed_id AS user_id, 
                1 AS depth
            FROM follows f1
            JOIN follows f2 ON f1.followed_id = f2.follower_id
            WHERE f1.follower_id = :userId 
            AND f2.followed_id = :userId
        
            UNION
        
        
            SELECT 
                f3.followed_id, 
                ug.depth + 1
            FROM follows f3
            JOIN follows f4 ON f3.followed_id = f4.follower_id
            JOIN user_graph ug ON f3.follower_id = ug.user_id
            WHERE f4.followed_id = ug.user_id
            AND ug.depth < 2
            AND f3.followed_id != :userId -- Evitar volver a mí mismo
        )
        
        
        SELECT u.* FROM users u
        WHERE u.id IN (SELECT user_id FROM user_graph)
        AND u.status = :#{#status.name()}
    """, nativeQuery = true)
    List<UserModel> findMutualsFollowers(
        @Param("userId") String userId,
        @Param("status") Status status
    );
}
