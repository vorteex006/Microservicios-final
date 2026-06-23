package com.example.NotificacionService.config;

import com.example.NotificacionService.Modelo.Notificacion;
import com.example.NotificacionService.Repository.NotificacionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Random;



@Profile("dev")
@Component
@RequiredArgsConstructor
@Slf4j
public class DataLoader implements CommandLineRunner {

    private final NotificacionRepository repository;

    @Override
    public void run(String... args) {
        if (repository.count() == 0) {
            Faker faker = new Faker();
            Random random = new Random();

            // Mensajes base del sistema
            String[] mensajesBase = {
                "Bienvenido a la plataforma",
                "Tu postulación fue revisada",
                "Tu perfil ha sido actualizado",
                "Nueva oferta laboral disponible",
                "Tu evaluación fue procesada"
            };

            // Notificaciones con mensajes base
            for (String mensaje : mensajesBase) {
                repository.save(new Notificacion(
                        null,
                        (long) faker.number().numberBetween(1, 10),
                        mensaje,
                        false
                ));
            }

            // Notificaciones adicionales generadas con DataFaker
            for (int i = 0; i < 5; i++) {
                repository.save(new Notificacion(
                        null,
                        (long) faker.number().numberBetween(1, 10),
                        faker.lorem().sentence(6),
                        random.nextBoolean()
                ));
            }

            log.info(">> Notificaciones generadas (base + DataFaker)");
        }
    }
}
