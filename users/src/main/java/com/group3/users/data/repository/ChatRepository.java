package com.group3.users.data.repository;

import com.group3.users.data.datasource.chat_server.repository.ChatServerRepositoryI;
import com.group3.users.domain.repository.ChatRepositoryI;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class ChatRepository implements ChatRepositoryI {

    private final ChatServerRepositoryI repository;

    @Override
    public void deleteUserHistory(String userId, String secret) {
        this.repository.deleteUserHistory(userId, secret);
    }

}
