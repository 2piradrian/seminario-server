package com.group3.chat.data.datasource.postgres.repository;

import com.group3.chat.data.datasource.postgres.model.ChatMessageModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostgresChatMessageRepositoryI extends JpaRepository<ChatMessageModel, String> {

    @Query("""
        SELECT m
        FROM ChatMessageModel m
        WHERE (m.senderId = :user1Id AND m.receiverId = :user2Id)
           OR (m.senderId = :user2Id AND m.receiverId = :user1Id)
        ORDER BY m.createdAt ASC
    """)
    Page<ChatMessageModel> findConversation(
            @Param("user1Id") String user1Id,
            @Param("user2Id") String user2Id,
            Pageable pageable
    );

    @Query("""
        SELECT DISTINCT CASE
            WHEN m.senderId = :userId THEN m.receiverId
            ELSE m.senderId
        END
        FROM ChatMessageModel m
        WHERE m.senderId = :userId OR m.receiverId = :userId
    """)
    List<String> findActiveChats(@Param("userId") String userId);

}