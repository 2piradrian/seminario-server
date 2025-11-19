package com.group3.notifications.config.helpers;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class SecretKeyHelper {

    private final String secret;

    public SecretKeyHelper(@Value("${application.internal.secret}") String secret) {
        this.secret = secret;
    }

    public boolean isValid(String provided) {
        return secret.equals(provided);
    }

}