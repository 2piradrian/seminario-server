package com.group3.auth.data.postgres.repository;

import com.group3.auth.data.postgres.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostgresUserRepositoryI extends JpaRepository<UserModel, String> {

  Optional<UserModel> findByEmail(String email);

  @Query(value = "SELECT * FROM users u " +
    "WHERE LOWER(u.name) LIKE LOWER(CONCAT('%', :name, '%')) " +
    "AND LOWER(u.surname) LIKE LOWER(CONCAT('%', :surname, '%'))",
    nativeQuery = true)
  List<UserModel> findByFullNameLike(@Param("name") String name,
                                     @Param("surname") String surname);

}
