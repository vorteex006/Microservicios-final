package com.example.NotificacionService.Modelo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "notificacion")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entidad que representa una notificación enviada a un usuario")
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID autogenerado de la notificación", example = "1")
    private Long id;

    @Column(nullable = false)
    @Schema(description = "ID del usuario que recibe la notificación", example = "5")
    private Long idUsuario;

    @Column(nullable = false)
    @Schema(description = "Contenido del mensaje", example = "Tu postulación fue revisada")
    private String mensaje;

    @Column(nullable = false)
    @Schema(description = "Indica si el usuario ya leyó la notificación", example = "false")
    private Boolean leido;
}
