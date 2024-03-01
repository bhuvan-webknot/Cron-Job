package com.example.cronnotificationsender.CronNotificationSender.service;

import com.example.cronnotificationsender.CronNotificationSender.models.User;
import com.example.cronnotificationsender.CronNotificationSender.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@Slf4j
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private JavaMailSender mailSender;

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
        log.info("Mail Sent to ..."+toEmail);
    }

    // Every day at 5pm
    @Scheduled(cron = "40 35 10 * * *")
    public void sendDailyEmail() {
        // Fetch all users subscribed to daily emails
        List<User> users = userRepository.findAll();
        users.forEach(user -> {
            String subject = "Daily BlogPost";
            String text = "Hello "+ user.getUserName() +"\nThis is your daily blogpost email.";
            user.getSubscribedTo().forEach(ele->{
                if(ele=="DAILY")
                    sendEmail(user.getUserEmail(),text,subject);
            });
        });
    }

    // Every week on Sunday at 7pm
    @Scheduled(cron = "50 38 10 * * *")
    public void sendWeeklyEmail() {
        // Fetch all users subscribed to weekly emails
        List<User> users = userRepository.findAll();
        users.forEach(user -> {
            String subject = "Weekly BlogPost";
            String text = "Hello "+ user.getUserName() +"\nThis is your weekly blogpost email.";
            user.getSubscribedTo().forEach(ele->{
                log.info(String.valueOf(ele));
                if(ele=="WEEKLY")
                    sendEmail(user.getUserEmail(),text,subject);
            });
        });
    }


    // Every month on the first day at 7pm
//    @Scheduled(cron = "0 0 19 1 * *")
//    public void sendMonthlyEmail() {
//        // Fetch all users subscribed to monthly emails
//        List<User> users = userRepository.findAll();
//        users.forEach(user -> {
//            String subject = "Weekly BlogPost";
//            String text = "Hello "+ user.getUserName() +"\nThis is your weekly blogpost email.";
//            user.getSubscribedTo().forEach(ele->{
//                if(ele==MONTHLY)
//                    sendEmail(user.getUserEmail(),text,subject);
//            });
//        });
//    }



}
