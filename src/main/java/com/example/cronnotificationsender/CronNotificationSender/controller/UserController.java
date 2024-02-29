package com.example.cronnotificationsender.CronNotificationSender.controller;


import com.example.cronnotificationsender.CronNotificationSender.models.User;
import com.example.cronnotificationsender.CronNotificationSender.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class UserController {
    @Autowired
    UserService userService;
//    @Autowired
//    EmailSenderService emailSenderService;

    @GetMapping("/fetch-users")
    public ResponseEntity<List<User>> fetchUserList() {
        return new ResponseEntity<>(userService.fetchAllUsers(), HttpStatusCode.valueOf(200));
    }

    @PostMapping("/create-user")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.saveUser(user), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/send-daily-mail")
    public void sendDailyMail(){
       userService.sendDailyEmail();
    }

    @GetMapping("/send-weekly-mail")
    public void sendWeklyMail(){
        userService.sendWeeklyEmail();
    }

    @GetMapping("/send-monthly-mail")
    public void sendMonthlyMail(){
        userService.sendMonthlyEmail();
    }
}
