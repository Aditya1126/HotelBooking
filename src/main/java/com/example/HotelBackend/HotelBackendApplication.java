package com.example.HotelBackend;

import com.example.HotelBackend.dto.NotificationDTO;
import com.example.HotelBackend.enums.NotificationType;
import com.example.HotelBackend.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class HotelBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelBackendApplication.class, args);
	}
}
