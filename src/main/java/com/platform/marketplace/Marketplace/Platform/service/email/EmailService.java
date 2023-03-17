package com.platform.marketplace.Marketplace.Platform.service.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {


    private final JavaMailSender mailSender;

    private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);
    @Async
    public void send(String to, String email) {
        try {
            MimeMessage mimeMessage =mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(email , true);
            helper.setTo(to);
            helper.setSubject("Confirm your email");
            helper.setFrom("otmene@abv.bg");
        }catch (MessagingException e){
            LOGGER.error("failed to send email" , e);
            throw new IllegalStateException("failed to send email");
        }
    }
}
