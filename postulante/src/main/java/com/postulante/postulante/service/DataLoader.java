package com.postulante.postulante.service;

import com.postulante.postulante.model.Postulante;
import com.postulante.postulante.repository.PostulanteRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private PostulanteRepository postulanteRepository;

    @Override
    public void run(String... args) throws Exception {
        if (postulanteRepository.count() > 0) {
            return;
        }

        Faker faker = new Faker();

        // Generar 50 postulantes
        for (int i = 0; i < 50; i++) {
            Postulante postulante = new Postulante();

            postulante.setNombre(faker.name().firstName());
            postulante.setApellido(faker.name().lastName());
            postulante.setCorreo(faker.internet().emailAddress());

            postulante.setHabilidad(faker.programmingLanguage().name());

            postulanteRepository.save(postulante);
        }

        System.out.println("Se han cargado 50 postulantes de prueba.");
    }
}