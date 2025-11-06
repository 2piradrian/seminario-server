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
        u.status = :status AND
        (:#{#fullName == null or #fullName.isEmpty()} = true OR LOWER(CONCAT(u.profile.name, ' ', u.profile.surname)) LIKE LOWER(CONCAT('%', :fullName, '%'))) AND
        (:#{#styles == null or #styles.isEmpty()} = true OR EXISTS (SELECT 1 FROM u.profile.styles s WHERE s IN :styles)) AND
        (:#{#instruments == null or #instruments.isEmpty()} = true OR EXISTS (SELECT 1 FROM u.profile.instruments i WHERE i IN :instruments))
    """)
    Page<UserModel> findByFilteredPage(
        @Param("fullName") String fullName,
        @Param("status") Status status,
        @Param("styles") List<String> styles,
        @Param("instruments") List<String> instruments,
        Pageable pageable
    );

}
