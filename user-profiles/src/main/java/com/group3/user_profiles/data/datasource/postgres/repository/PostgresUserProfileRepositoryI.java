package com.group3.user_profiles.data.datasource.postgres.repository;

import com.group3.entity.Instrument;
import com.group3.entity.Status;
import com.group3.entity.Style;
import com.group3.user_profiles.data.datasource.postgres.model.UserProfileModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostgresUserProfileRepositoryI extends JpaRepository<UserProfileModel, String> {

    // ======== Search ========

    Optional<UserProfileModel> findByEmail(String email);

    List<UserProfileModel> findAllByIdIn(List<String> ids);


    // ======== Get filtered By Filtered Pafe ========

    @Query(value = """
        SELECT DISTINCT u
        FROM UserProfileModel u
        LEFT JOIN u.styles s
        LEFT JOIN u.instruments i
        WHERE u.status = :status
        AND (LOWER(CONCAT(u.name, ' ', u.surname)) LIKE LOWER(CONCAT('%', :fullName, '%')))
        AND (:#{#styles.isEmpty()} = true OR s IN :styles)
        AND (:#{#instruments.isEmpty()} = true OR i IN :instruments)
        AND (:#{#idsProfile.isEmpty()} = true OR u.id IN :idsProfile)
    """,
        countQuery = """
        SELECT COUNT(DISTINCT u.id)
        FROM UserProfileModel u
        LEFT JOIN u.styles s
        LEFT JOIN u.instruments i
        WHERE u.status = :status
        AND (LOWER(CONCAT(u.name, ' ', u.surname)) LIKE LOWER(CONCAT('%', :fullName, '%')))
        AND (:#{#styles.isEmpty()} = true OR s IN :styles)
        AND (:#{#instruments.isEmpty()} = true OR i IN :instruments)
        AND (:#{#idsProfile.isEmpty()} = true OR u.id IN :idsProfile)
    """)
    Page<UserProfileModel> findByFilteredPage(
        @Param("fullName") String fullName,
        @Param("status") Status status,
        @Param("styles") List<String> styles,           // Corregido: de List<Style> a List<String>
        @Param("instruments") List<String> instruments, // Corregido: de List<Instrument> a List<String>
        @Param("idsProfile") List<String> idsProfile,     // Corregido: de List<Integer> a List<String>
        Pageable pageable
    );


    // ======== (followers / following) ========

    @Query(
            value = "SELECT user_id FROM user_following WHERE following_id = :userId ORDER BY user_id ASC",
            countQuery = "SELECT COUNT(*) FROM user_following WHERE following_id = :userId",
            nativeQuery = true
    )
    Page<String> findFollowers(
            @Param("userId") String userId,
            Pageable pageable
    );

    @Query(
            value = "SELECT following_id FROM user_following WHERE user_id = :userId ORDER BY following_id ASC",
            countQuery = "SELECT COUNT(*) FROM user_following WHERE user_id = :userId",
            nativeQuery = true
    )
    Page<String> findFollowing(
            @Param("userId") String userId,
            Pageable pageable
    );


    // ======== Count ========

    @Query(
            value = "SELECT COUNT(*) FROM user_following WHERE user_id = :userId",
            nativeQuery = true
    )
    Integer countFollowing(@Param("userId") String userId);

    @Query(
            value = "SELECT COUNT(*) FROM user_following WHERE following_id = :userId",
            nativeQuery = true
    )
    Integer countFollowers(@Param("userId") String userId);

}
