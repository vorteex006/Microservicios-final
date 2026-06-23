package com.example.CategoriaService.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "DTO para crear o actualizar una categoría")
public class CategoriaDTO {

    @NotBlank(message = "El nombre de la categoría no puede estar vacío")
    @Schema(description = "Nombre de la categoría", example = "Informática", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nombre;
}
