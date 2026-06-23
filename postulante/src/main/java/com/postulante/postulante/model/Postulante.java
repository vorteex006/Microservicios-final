package com.postulante.postulante.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "postulantes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Datos basicos del postulante") 
public class Postulante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID auto-incremental del postulante", example = "1")
    private Long id;

    @Schema(description = "Nombre del postulante", example = "Gonzalo")
    private String nombre;

    @Schema(description = "Apellido del postulante", example = "Perez")
    private String apellido;

    @Schema(description = "Correo electronico de contacto", example = "gonzalo.perez@mail.com")
    private String correo;

    @Schema(description = "Habilidad principal del postulante", example = "Spring Boot")
    private String habilidad;
}