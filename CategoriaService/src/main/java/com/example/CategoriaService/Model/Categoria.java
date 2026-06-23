package com.example.CategoriaService.Model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "categoria")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entidad que representa una categoría del mercado laboral")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID autogenerado de la categoría", example = "1")
    private Long id;

    @Column(nullable = false)
    @Schema(description = "Nombre de la categoría", example = "Informática")
    private String nombre;
}
