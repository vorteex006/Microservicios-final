package com.example.NotificacionService.Repository;

import com.example.NotificacionService.Modelo.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {

    // TIPO 1: QUERY METHOD
    // → SELECT * FROM notificacion WHERE id_usuario = ?
    List<Notificacion> findByIdUsuario(Long idUsuario);

    // TIPO 2: JPQL
    // → Trae todas las notificaciones no leídas de un usuario
    @Query("SELECT n FROM Notificacion n WHERE n.idUsuario = :idUsuario AND n.leido = false")
    List<Notificacion> findNoLeidasPorUsuario(@Param("idUsuario") Long idUsuario);
}
