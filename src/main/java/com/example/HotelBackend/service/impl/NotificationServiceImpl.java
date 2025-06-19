package com.example.HotelBackend.service.impl;


import com.example.HotelBackend.dto.NotificationDTO;
import com.example.HotelBackend.entities.Notification;
import com.example.HotelBackend.enums.NotificationType;
import com.example.HotelBackend.repositories.NotificationRepository;
import com.example.HotelBackend.service.NotificationService;
import com.twilio.rest.api.v2010.account.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.twilio.type.PhoneNumber;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {


    private final JavaMailSender javaMailSender;

    private final NotificationRepository notificationRepository;

    @Value("${twilio.phone.number}")
    private String twilioPhoneNumber;

    @Value("${twilio.whatsapp.number}")
    private String twilioWhatsAppNumber;

    @Override
    @Async
    public void sendEmail(NotificationDTO notificationDTO) {
        log.info("Sending email ...");
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(notificationDTO.getRecipient());
        simpleMailMessage.setSubject(notificationDTO.getSubject());
        simpleMailMessage.setText(notificationDTO.getBody());
        javaMailSender.send(simpleMailMessage);

        saveNotification(notificationDTO, NotificationType.EMAIL);
    }

    @Override
    @Async
    public void sendSms(NotificationDTO notificationDTO) {
        log.info("Sending SMS to {}", notificationDTO.getRecipient());

        Message.creator(
                new PhoneNumber(notificationDTO.getRecipient()),  // E.g. +91XXXXXXXXXX
                new PhoneNumber(twilioPhoneNumber),
                notificationDTO.getBody()
        ).create();

        saveNotification(notificationDTO, NotificationType.SMS);
    }

    @Override
    @Async
    public void sendWhatsapp(NotificationDTO notificationDTO) {
        log.info("Sending WhatsApp to {}", notificationDTO.getRecipient());

        Message.creator(
                new PhoneNumber("whatsapp:" + notificationDTO.getRecipient()),  // E.g. +91XXXXXXXXXX
                new PhoneNumber(twilioWhatsAppNumber),
                notificationDTO.getBody()
        ).create();

        saveNotification(notificationDTO, NotificationType.WHATSAPP);
    }

    private void saveNotification(NotificationDTO dto, NotificationType type) {
        Notification notification = Notification.builder()
                .recipient(dto.getRecipient())
                .subject(dto.getSubject())
                .body(dto.getBody())
                .bookingReference(dto.getBookingReference())
                .type(type)
                .build();

        notificationRepository.save(notification);
    }
}
