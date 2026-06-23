package com.empresa.empresa.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EmpresaDTO {
    @NotBlank(message = "Nombre empresa obligatorio")
    private String nombre;
    private String sector;
}