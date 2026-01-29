package com.group3.users.presentation.service;

public interface EmailServiceI {

    void sendEmail(String from, String fromName, String to, String subject, String text);

}
