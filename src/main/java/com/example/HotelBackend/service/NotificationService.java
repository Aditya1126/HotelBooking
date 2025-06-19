package com.example.HotelBackend.service;


import com.example.HotelBackend.dto.NotificationDTO;

public interface NotificationService {

    void sendEmail(NotificationDTO notificationDTO);

    void sendSms(NotificationDTO notificationDTO);

    void sendWhatsapp(NotificationDTO notificationDTO);
}
