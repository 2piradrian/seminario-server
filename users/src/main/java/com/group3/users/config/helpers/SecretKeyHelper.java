package com.group3.users.config.helpers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SecretKeyHelper {

    private final String secret;

    public SecretKeyHelper(@Value("${app.internal-secret}") String secret) {
        this.secret = secret;
    }

    public String getSecret() {
        return secret;
    }

    public boolean isValid(String provided) {
        return secret.equals(provided);
    }

}
