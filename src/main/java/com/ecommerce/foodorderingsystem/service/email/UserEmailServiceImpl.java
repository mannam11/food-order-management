package com.ecommerce.foodorderingsystem.service.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class UserEmailServiceImpl implements UserEmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    public UserEmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendSignupEmail(String to, String fullName) {

        String subject = "Welcome to FirstBite";
        String body = "Dear " + fullName + '\n' + '\n'+ "We are happy to have you onboard..."
                + '\n' + "Enjoy your time with delicious food "+'\n' + '\n' +"Regards" + '\n'+"FirstBite";

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }
}
