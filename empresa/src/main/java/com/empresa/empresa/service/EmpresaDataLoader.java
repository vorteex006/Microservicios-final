package com.empresa.empresa.service;

import com.empresa.empresa.model.Empresa;
import com.empresa.empresa.repository.EmpresaRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dev")
@Component
public class EmpresaDataLoader implements CommandLineRunner {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();

        // Generar 20 empresas de prueba
        for (int i = 0; i < 20; i++) {
            Empresa empresa = new Empresa();
            
            empresa.setId(null); 
            empresa.setNombre(faker.company().name());
            empresa.setSector(faker.company().industry());
            
            empresaRepository.save(empresa);
        }
        
        System.out.println("Se han cargado 20 empresas de prueba ");
    }
}