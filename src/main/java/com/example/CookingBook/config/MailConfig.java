package com.example.CookingBook.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    @Bean
    public JavaMailSender javaMailSender(
            @Value("${mail.host}") String mailHost,
            @Value("${mail.port}") Integer mailPort,
            @Value("${mail.username}") String mailUsername,
            @Value("${mail.password}") String mailPassword
    ) {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        javaMailSender.setHost(mailHost);
        javaMailSender.setPort(mailPort);
        javaMailSender.setUsername(mailUsername);
        javaMailSender.setPassword(mailPassword);
        javaMailSender.setJavaMailProperties(mailProperties(mailHost, mailPort));
        javaMailSender.setDefaultEncoding("UTF-8");

        return javaMailSender;
    }

    private Properties mailProperties(String host, Integer port) {
        Properties prop = new Properties();

        boolean enableSSL = true;
        prop.put("mail.smtp.host", host);
        prop.put("mail.smtp.port", port);
        prop.put("mail.smtp.ssl.trust", host);
        prop.put("mail.smtp.auth", "true"); //enable authentication
        prop.put("mail.smtp.ssl.enable", enableSSL);
        prop.put("mail.debug", "true");
        return prop;
    }
}
