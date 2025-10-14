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


    // ======== Get filtered By Filtered Page ========

    @Query("""
        SELECT p FROM UserProfileModel p WHERE
        p.status = :status AND
        (:fullName IS NULL OR LOWER(CONCAT(p.name, ' ', p.surname)) LIKE LOWER(CONCAT('%', :fullName, '%'))) AND
        (:#{#styles == null or #styles.isEmpty()} = true OR p.id IN (SELECT p_s.id FROM UserProfileModel p_s JOIN p_s.styles s WHERE s IN :styles)) AND
        (:#{#instruments == null or #instruments.isEmpty()} = true OR p.id IN (SELECT p_i.id FROM UserProfileModel p_i JOIN p_i.instruments i WHERE i IN :instruments)) AND
        (:#{#idsProfile == null or #idsProfile.isEmpty()} = true OR p.id IN (SELECT p_f.id FROM UserProfileModel p_f JOIN p_f.following f WHERE f IN :idsProfile))
    """)
    Page<UserProfileModel> findByFilteredPage(
        @Param("fullName") String fullName,
        @Param("status") Status status,
        @Param("styles") List<String> styles,
        @Param("instruments") List<String> instruments,
        @Param("idsProfile") List<String> idsProfile,
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
            value = "SELECT COUNT(*) FROM user_following WHERE user_id = :id",
            nativeQuery = true
    )
    Integer countFollowing(@Param("id") String id);

    @Query(
            value = "SELECT COUNT(*) FROM user_following WHERE following_id = :id",
            nativeQuery = true
    )
    Integer countFollowers(@Param("id") String id);

}
