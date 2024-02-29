package com.example.cronnotificationsender.CronNotificationSender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CronNotificationSenderApplication {

	public static void main(String[] args) {
		SpringApplication.run(CronNotificationSenderApplication.class, args);
	}

}
