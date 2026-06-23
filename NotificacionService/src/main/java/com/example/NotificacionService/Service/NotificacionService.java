package com.example.NotificacionService.Service;

import com.example.NotificacionService.Modelo.Notificacion;
import com.example.NotificacionService.Repository.NotificacionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class NotificacionService {

    @Autowired
    private NotificacionRepository repository;

    public List<Notificacion> findAll() {
        return repository.findAll();
    }

    public Notificacion findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notificacion no encontrada con id: " + id));
    }

    public Notificacion save(Notificacion notificacion) {
        return repository.save(notificacion);
    }

    public void delete(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notificacion no encontrada con id: " + id));
        repository.deleteById(id);
    }

    // Métodos para HATEOAS V2
    public List<Notificacion> findByUsuario(Long idUsuario) {
        return repository.findByIdUsuario(idUsuario);
    }

    public List<Notificacion> findNoLeidasPorUsuario(Long idUsuario) {
        return repository.findNoLeidasPorUsuario(idUsuario);
    }
}
