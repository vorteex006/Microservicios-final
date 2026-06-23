package com.example.NotificacionService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class NotificacionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificacionServiceApplication.class, args);
	}

}
//http://localhost:8099/swagger-ui.html http://localhost:8099/api/v1/notificaciones