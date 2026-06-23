package com.postulante.postulante.repository;

import com.postulante.postulante.model.Postulante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostulanteRepository extends JpaRepository<Postulante, Long> {

    @Query("SELECT p FROM Postulante p WHERE p.correo = :correo")
    List<Postulante> buscarPorCorreoExacto(@Param("correo") String correo);

}
