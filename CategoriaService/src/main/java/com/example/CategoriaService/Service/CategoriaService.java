package com.example.CategoriaService.Service;

import com.example.CategoriaService.Model.Categoria;
import com.example.CategoriaService.Repository.CategoriaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public List<Categoria> findAll() {
        return repository.findAll();
    }

    public Categoria findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con id: " + id));
    }

    public Categoria save(Categoria categoria) {
        return repository.save(categoria);
    }

    public void delete(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con id: " + id));
        repository.deleteById(id);
    }

    // Metodo para HATEOAS V2: búsqueda parcial por nombre
    public List<Categoria> buscarPorNombre(String texto) {
        return repository.buscarPorNombreJPQL(texto);
    }
}
