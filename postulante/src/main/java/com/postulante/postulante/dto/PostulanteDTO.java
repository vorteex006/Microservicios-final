package com.postulante.postulante.dto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class PostulanteDTO {
    @NotBlank(message = "Nombre obligatorio")
    private String nombre;
    @NotBlank(message = "Apellido obligatorio")
    private String apellido;
    @NotBlank(message = "Correo obligatorio")
    private String correo;
    private String habilidad;
}