package com.group3.users.presentation.service;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendEmail(
        String to,
        String subject,
        String text
    ) {
        try {
            var message = mailSender.createMimeMessage();
            var helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true);

            mailSender.send(message);
        }
        catch (MessagingException e){
            throw new ErrorHandler(ErrorType.INTERNAL_ERROR);
        }

    }


}
