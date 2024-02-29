package com.example.cronnotificationsender.CronNotificationSender.service;

import com.example.cronnotificationsender.CronNotificationSender.models.User;
import com.example.cronnotificationsender.CronNotificationSender.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

import static com.example.cronnotificationsender.CronNotificationSender.models.SubscriptionType.DAILY;
import static com.example.cronnotificationsender.CronNotificationSender.models.SubscriptionType.WEEKLY;
import static com.example.cronnotificationsender.CronNotificationSender.models.SubscriptionType.MONTHLY;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private JavaMailSender mailSender;

    Logger logger = LoggerFactory.getLogger(UserService.class);

    public List<User> fetchAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void sendEmail(String toEmail, String body, String subject) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("1rn20cs033.bhuvans@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);

        mailSender.send(message);
        logger.info("Mail Sent to ..."+toEmail);
    }

    // Every day at 5pm
    @Scheduled(cron = "0 19 17 * * *")
    public void sendDailyEmail() {
        // Fetch all users subscribed to daily emails
        List<User> users = userRepository.findAll();
        users.forEach(user -> {
            String subject = "Daily BlogPost";
            String text = "Hello "+ user.getUserName() +"\nThis is your daily blogpost email.";

            if(user.getSubscribedTo()==DAILY)
                sendEmail(user.getUserEmail(),text,subject);
        });
    }

    // Every week on Sunday at 7pm
    @Scheduled(cron = "0 0 19 * * SUN")
    public void sendWeeklyEmail() {
        // Fetch all users subscribed to weekly emails
        List<User> users = userRepository.findAll();
        users.forEach(user -> {
            String subject = "Weekly BlogPost";
            String text = "Hello "+ user.getUserName() +"\nThis is your weekly blogpost email.";
            if(user.getSubscribedTo()==WEEKLY)
                sendEmail(user.getUserEmail(),text,subject);
        });
    }


    // Every month on the first day at 7pm
    @Scheduled(cron = "0 0 19 1 * *")
    public void sendMonthlyEmail() {
        // Fetch all users subscribed to monthly emails
        List<User> users = userRepository.findAll();
        users.forEach(user -> {
            String subject = "Weekly BlogPost";
            String text = "Hello "+ user.getUserName() +"\nThis is your weekly blogpost email.";
            if(user.getSubscribedTo()==MONTHLY)
                sendEmail(user.getUserEmail(),text,subject);
        });
    }



}
