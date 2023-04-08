package com.example.CookingBook.sheduleJobs.implementation;

import com.example.CookingBook.models.DTO.UserDTO;
import com.example.CookingBook.services.EmailService;
import com.example.CookingBook.services.UserService;
import com.example.CookingBook.sheduleJobs.constants.EmailConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Configuration
@EnableScheduling
public class EmailScheduleJob {
    private final UserService userService;
    private final EmailService emailService;

    @Autowired
    public EmailScheduleJob(UserService userService, EmailService emailService) {
        this.userService = userService;
        this.emailService = emailService;
    }

    @Async
    @Scheduled(cron = "0 0 */12 * * *")
    public void newsletter12Hours() {
        List<UserDTO> users = this.userService.findAllUsers();
        users.forEach(user -> emailService.sendSimpleChristmasEmail(
                user.getEmail(), user.getFirstName() + " " + user.getLastName(), EmailConstants.NEWSLETTER_MESSAGE_TITLE, EmailConstants.NEWSLETTER_MESSAGE_TEXT));
    }

    @Async
    @Scheduled(cron = "0 0 12 24 12 ?")
    public void christmasScheduleJob() {
        List<UserDTO> users = this.userService.findAllUsers();

        users.forEach(user -> emailService.sendSimpleChristmasEmail(
                user.getEmail(), user.getFirstName() + " " + user.getLastName(), EmailConstants.CHRISTMAS_MESSAGE_TITLE, EmailConstants.CHRISTMAS_MESSAGE_TEXT));
    }
}
