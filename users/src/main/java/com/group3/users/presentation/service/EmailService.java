package com.group3.users.presentation.service;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Slf4j
@Service
@AllArgsConstructor
public class EmailService implements EmailServiceI {

    private final JavaMailSender mailSender;

    public void sendEmail(
        String from,
        String fromName,
        String to,
        String subject,
        String text
    ) {
        try {
            var message = mailSender.createMimeMessage();
            var helper = new MimeMessageHelper(message, true);

            helper.setFrom(from, fromName);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true);

            ClassPathResource logoResource = new ClassPathResource("email-logo.png");

            if (logoResource.exists()) {
                helper.addInline("fortuneLogo", logoResource);
            }

            mailSender.send(message);
        }
        catch (MessagingException | UnsupportedEncodingException e){
            throw new ErrorHandler(ErrorType.INTERNAL_ERROR);
        }

    }


}
