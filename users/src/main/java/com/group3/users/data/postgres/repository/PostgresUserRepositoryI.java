package com.group3.users.data.postgres.repository;

import com.group3.users.data.postgres.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface PostgresUserRepositoryI extends JpaRepository<UserModel, String> {

    Optional<UserModel> findByEmail(String email);

    @Query("""
        SELECT u
        FROM UserModel u
        WHERE LOWER(u.name) LIKE LOWER(CONCAT('%', :firstName, '%'))
        AND LOWER(u.surname) LIKE LOWER(CONCAT('%', :surName, '%'))
    """)
    List<UserModel> findByFullNameLike(@Param("name") String name, @Param("surname") String surname);

}
