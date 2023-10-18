package com.shop.notificationservice.service;

import com.shop.notificationservice.entity.EmailMessage;
import com.shop.notificationservice.entity.Transaction;
import jakarta.mail.MessagingException;

import java.io.UnsupportedEncodingException;

public interface EmailService {
    void sendEmail(Transaction transaction) throws MessagingException, UnsupportedEncodingException;
    void getEmail(String keySearch);
    void deleteEmail(String keySearch);
}
