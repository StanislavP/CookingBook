package com.example.CookingBook.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Locale;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    public EmailService(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    public void sendRegistrationEmail(String userEmail, String userName, String activationLink) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

        try {
            mimeMessageHelper.setFrom("no-replay-cookingbook@abv.bg");
            mimeMessageHelper.setTo(userEmail);
            //TODO: i18n
            mimeMessageHelper.setSubject("Welcome to Cooking book!");
            mimeMessageHelper.setText(generateEmailText(userName, activationLink), true);

            javaMailSender.send(mimeMessageHelper.getMimeMessage());

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendSimpleChristmasEmail(String userEmail, String userName, String subject, String message) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

        try {
            mimeMessageHelper.setFrom("no-replay-cookingbook@abv.bg");
            mimeMessageHelper.setTo(userEmail);
            //TODO: i18n
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(generateChristmasEmailText(userName,message), true);

            javaMailSender.send(mimeMessageHelper.getMimeMessage());

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateEmailText(String userName, String activationLink) {
        Context ctx = new Context();
        ctx.setLocale(Locale.getDefault());
        ctx.setVariable("userName", userName);
        ctx.setVariable("activationLink", activationLink);

        return templateEngine.process("email/registration", ctx);
    }

    private String generateChristmasEmailText(String userName, String message) {
        Context ctx = new Context();
        ctx.setLocale(Locale.getDefault());
        ctx.setVariable("userName", userName);
        ctx.setVariable("message", message);

        return templateEngine.process("email/christmas", ctx);
    }


}
