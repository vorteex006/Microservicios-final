package com.empresa.empresa.service;

import com.empresa.empresa.model.Empresa;
import com.empresa.empresa.repository.EmpresaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import com.empresa.empresa.client.PostulanteFeignClient; 

@Service
@RequiredArgsConstructor
public class EmpresaService {

    private final EmpresaRepository repository;
    
  
    private final PostulanteFeignClient postulanteClient; 

    public List<Empresa> listar() { return repository.findAll(); }
    public Empresa guardar(Empresa e) { return repository.save(e); }

    public void buscarDatosDeUnPostulante(Long id) {
        
        Object postulante = postulanteClient.getPostulanteById(id);
        System.out.println("Datos recibidos del otro microservicio: " + postulante);
    }
}