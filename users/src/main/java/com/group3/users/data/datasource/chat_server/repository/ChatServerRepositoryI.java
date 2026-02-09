package com.group3.users.data.datasource.chat_server.repository;

import com.group3.users.config.beans.LoadBalancerConfiguration;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "chat-server", path = "/chat-server")
@LoadBalancerClient(name = "chat-server", configuration = LoadBalancerConfiguration.class)
public interface ChatServerRepositoryI {

    @DeleteMapping("/api/chat/user-history")
    void deleteUserHistory(
            @RequestParam("userId") String userId,
            @RequestParam("secret") String secret
    );

}
