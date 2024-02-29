package com.example.cronnotificationsender.CronNotificationSender.repository;

import com.example.cronnotificationsender.CronNotificationSender.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
