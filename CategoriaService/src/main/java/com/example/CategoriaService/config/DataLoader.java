package com.example.CategoriaService.config;

import com.example.CategoriaService.Model.Categoria;
import com.example.CategoriaService.Repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


//datafaker


@Profile("dev")
@Component
@RequiredArgsConstructor
@Slf4j
public class DataLoader implements CommandLineRunner {

    private final CategoriaRepository repository;

    @Override
    public void run(String... args) {
        if (repository.count() == 0) {
            Faker faker = new Faker();

            // Categorías base del mercado laboral (datos fijos)
            repository.save(new Categoria(null, "Informática"));
            repository.save(new Categoria(null, "Salud"));
            repository.save(new Categoria(null, "Construcción"));
            repository.save(new Categoria(null, "Educación"));
            repository.save(new Categoria(null, "Administración"));

            // Categorías adicionales generadas con DataFaker
            for (int i = 0; i < 5; i++) {
                repository.save(new Categoria(null, faker.job().field()));
            }

            log.info(">> Categorías generadas (base + DataFaker)");
        }
    }
}
