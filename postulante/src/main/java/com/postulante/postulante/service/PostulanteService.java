package com.postulante.postulante.service;

import com.postulante.postulante.model.Postulante;
import com.postulante.postulante.repository.PostulanteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class PostulanteService {

    private final PostulanteRepository repository;

    // Obtener todos los postulantes
    public List<Postulante> listar() {
        return repository.findAll();
    }

    // Buscar un postulante por Id
    public Postulante buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Postulante no encontrado con ID: " + id));
    }

    // Guardar un nuevo postulante 
    public Postulante guardar(Postulante p) {
        if (p.getNombre() == null || p.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre del postulante es obligatorio");
        }
        return repository.save(p);
    }

    // Eliminar un postulante
    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar: Postulante no existe con ID: " + id);
        }
        repository.deleteById(id);
    }

    // Busqueda por email
    public List<Postulante> buscarPorEmail(String correo) {
        return repository.buscarPorCorreoExacto(correo);
    }
}
