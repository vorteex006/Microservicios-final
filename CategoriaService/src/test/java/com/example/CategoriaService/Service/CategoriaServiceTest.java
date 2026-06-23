package com.example.CategoriaService.Service;

import com.example.CategoriaService.Model.Categoria;
import com.example.CategoriaService.Repository.CategoriaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoriaServiceTest {

    @InjectMocks
    private CategoriaService categoriaService;

    @Mock
    private CategoriaRepository categoriaRepository;

    @Test
    @DisplayName("findAll retorna lista con categorias")
    public void testFindAll() {

        when(categoriaRepository.findAll()).thenReturn(
                List.of(new Categoria(1L, "Informática"))
        );


        List<Categoria> categorias = categoriaService.findAll();


        assertNotNull(categorias);
        assertEquals(1, categorias.size());
    }

    @Test
    @DisplayName("findById retorna categoria existente")
    public void testFindById() {

        Categoria categoria = new Categoria(1L, "Salud");
        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoria));

        Categoria resultado = categoriaService.findById(1L);


        assertNotNull(resultado);
        assertEquals("Salud", resultado.getNombre());
    }

    @Test
    @DisplayName("findById lanza excepcion cuando id no existe")
    public void testFindByIdNotFound() {

        when(categoriaRepository.findById(99L)).thenReturn(Optional.empty());


        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> categoriaService.findById(99L));
        assertEquals("Categoría no encontrada con id: 99", ex.getMessage());
    }

    @Test
    @DisplayName("save guarda y retorna categoria con id")
    public void testSave() {

        Categoria nueva = new Categoria(null, "Construcción");
        Categoria guardada = new Categoria(1L, "Construcción");
        when(categoriaRepository.save(nueva)).thenReturn(guardada);


        Categoria resultado = categoriaService.save(nueva);


        assertNotNull(resultado.getId());
        assertEquals(1L, resultado.getId());
        verify(categoriaRepository, times(1)).save(nueva);
    }

    @Test
    @DisplayName("delete elimina categoria existente")
    public void testDelete() {

        Categoria categoria = new Categoria(1L, "Informática");
        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoria));


        categoriaService.delete(1L);

        verify(categoriaRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("buscarPorNombre retorna lista filtrada")
    public void testBuscarPorNombre() {

        when(categoriaRepository.buscarPorNombreJPQL("Inform")).thenReturn(
                List.of(new Categoria(1L, "Informática"))
        );

        List<Categoria> resultado = categoriaService.buscarPorNombre("Inform");


        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Informática", resultado.get(0).getNombre());
    }
}