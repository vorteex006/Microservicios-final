package com.postulante.postulante;
import com.postulante.postulante.model.Postulante;
import com.postulante.postulante.repository.PostulanteRepository;
import com.postulante.postulante.service.PostulanteService;

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
public class PostulanteServiceTest {

    @InjectMocks
    private PostulanteService postulanteService;

    @Mock
    private PostulanteRepository postulanteRepository;

    @Test
    public void testListar() {
        Postulante mockPostulante = new Postulante(1L, "Felipe", "Oyarzo", "felipe@correo.com", "Java");
        when(postulanteRepository.findAll()).thenReturn(List.of(mockPostulante));

        List<Postulante> postulantes = postulanteService.listar();

        assertNotNull(postulantes);
        assertEquals(1, postulantes.size());
        assertEquals("Felipe", postulantes.get(0).getNombre());
    }

    @Test
    public void testBuscarPorId() {
        Postulante p = new Postulante(1L, "Ana", "Gomez", "ana@mail.com", "Spring");
        when(postulanteRepository.findById(1L)).thenReturn(Optional.of(p));

        Postulante resultado = postulanteService.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals("Ana", resultado.getNombre());
    }

    @Test
    public void testEliminar() {

        Long idExistente = 1L;

        when(postulanteRepository.existsById(idExistente)).thenReturn(true);

        postulanteService.eliminar(idExistente);

        verify(postulanteRepository).deleteById(idExistente);
    }



}
