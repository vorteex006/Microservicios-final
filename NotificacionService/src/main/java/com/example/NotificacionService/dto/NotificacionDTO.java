package com.example.NotificacionService.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "DTO para crear o actualizar una notificación")
public class NotificacionDTO {

    @NotNull(message = "idUsuario obligatorio")
    @Schema(description = "ID del usuario que recibe la notificación", example = "5", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long idUsuario;

    @NotBlank(message = "El mensaje no puede estar vacío")
    @Schema(description = "Contenido del mensaje", example = "Tu postulación fue revisada", requiredMode = Schema.RequiredMode.REQUIRED)
    private String mensaje;

    @NotNull(message = "El campo leido es obligatorio")
    @Schema(description = "Indica si el usuario ya leyó la notificación", example = "false", requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean leido;
}
