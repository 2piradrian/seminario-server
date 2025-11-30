package com.group3.chat.data.datasource.postgres.repository;

import com.group3.chat.data.datasource.postgres.model.ChatMessageModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostgresChatMessageRepositoryI extends JpaRepository<ChatMessageModel, String> {

    @Query("""
        SELECT m
        FROM ChatMessageModel m
        WHERE (m.senderId = :senderId AND m.receiverId = :receiverId)
           OR (m.senderId = :receiverId AND m.receiverId = :senderId)
        ORDER BY m.createdAt ASC
    """)
    Page<ChatMessageModel> findConversation(
            @Param("senderId") String senderId,
            @Param("receiverId") String receiverId,
            Pageable pageable
    );


}
