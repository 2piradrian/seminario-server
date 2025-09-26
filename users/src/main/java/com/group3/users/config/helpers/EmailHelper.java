package com.group3.users.config.helpers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EmailHelper {

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    // ${environmentVars.getUrl()}
    private String baseUrl;

    public String verifyEmailHTML(String token){

        String link = baseUrl + "/api/auth/verify-email/" + token;

        return """
            <h1>Verify your email</h1>
            <p>Click the link below to verify your email</p>
            <a href="%s">Verify email</a>
            """.formatted(link);

    }

}
