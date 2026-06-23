package com.empresa.empresa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema; 

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "empresas")
@Schema(description = "Datos de la empresa registrada en el sistema")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único de la empresa", example = "1")
    private Long id;

    @Schema(description = "Nombre comercial de la empresa", example = "Tech Solutions S.A.")
    private String nombre;

    @Schema(description = "Sector industrial al que pertenece", example = "Tecnología")
    private String sector;
}