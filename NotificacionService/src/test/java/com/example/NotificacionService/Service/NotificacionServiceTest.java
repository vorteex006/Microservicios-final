package com.example.NotificacionService.Service;

import com.example.NotificacionService.Modelo.Notificacion;
import com.example.NotificacionService.Repository.NotificacionRepository;
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
public class NotificacionServiceTest {

    @InjectMocks
    private NotificacionService notificacionService;

    @Mock
    private NotificacionRepository notificacionRepository;

    @Test
    @DisplayName("findAll retorna lista con notificaciones")
    public void testFindAll() {

        when(notificacionRepository.findAll()).thenReturn(
                List.of(new Notificacion(1L, 1L, "Bienvenido a la plataforma", false))
        );


        List<Notificacion> notificaciones = notificacionService.findAll();


        assertNotNull(notificaciones);
        assertEquals(1, notificaciones.size());
    }

    @Test
    @DisplayName("findById retorna notificacion existente")
    public void testFindById() {

        Notificacion notificacion = new Notificacion(1L, 1L, "Tu postulación fue revisada", false);
        when(notificacionRepository.findById(1L)).thenReturn(Optional.of(notificacion));


        Notificacion resultado = notificacionService.findById(1L);


        assertNotNull(resultado);
        assertEquals("Tu postulación fue revisada", resultado.getMensaje());
        assertFalse(resultado.getLeido());
    }

    @Test
    @DisplayName("findById lanza excepcion cuando id no existe")
    public void testFindByIdNotFound() {

        when(notificacionRepository.findById(99L)).thenReturn(Optional.empty());


        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> notificacionService.findById(99L));
        assertEquals("Notificacion no encontrada con id: 99", ex.getMessage());
    }

    @Test
    @DisplayName("save guarda y retorna notificacion con id")
    public void testSave() {

        Notificacion nueva = new Notificacion(null, 1L, "Nueva notificacion", false);
        Notificacion guardada = new Notificacion(1L, 1L, "Nueva notificacion", false);
        when(notificacionRepository.save(nueva)).thenReturn(guardada);


        Notificacion resultado = notificacionService.save(nueva);


        assertNotNull(resultado.getId());
        assertEquals(1L, resultado.getId());
        verify(notificacionRepository, times(1)).save(nueva);
    }

    @Test
    @DisplayName("delete elimina notificacion existente")
    public void testDelete() {

        Notificacion notificacion = new Notificacion(1L, 1L, "Mensaje", false);
        when(notificacionRepository.findById(1L)).thenReturn(Optional.of(notificacion));


        notificacionService.delete(1L);


        verify(notificacionRepository, times(1)).deleteById(1L);
    }
}