package com.empresa.empresa.repository;

import com.empresa.empresa.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;


public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

    @Query("SELECT e FROM Empresa e WHERE e.nombre = :nombre")
    List<Empresa> buscarPorNombreExacto(@Param("nombre") String nombre);
}