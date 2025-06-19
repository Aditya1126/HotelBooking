package com.example.HotelBackend.repositories;

import com.example.HotelBackend.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
