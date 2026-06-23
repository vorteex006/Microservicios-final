package com.example.NotificacionService.Controller;

import com.example.NotificacionService.Modelo.Notificacion;
import com.example.NotificacionService.Service.NotificacionService;
import com.example.NotificacionService.dto.NotificacionDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notificaciones")
@Tag(name = "Notificaciones", description = "Operaciones relacionadas con las notificaciones de usuarios")
public class NotificacionController {

    @Autowired
    private NotificacionService service;

    @Operation(summary = "Listar todas las notificaciones", description = "Retorna la lista completa de notificaciones registradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Notificacion.class))),
            @ApiResponse(responseCode = "204", description = "No hay notificaciones registradas")
    })
    @GetMapping
    public ResponseEntity<List<Notificacion>> listar() {
        List<Notificacion> lista = service.findAll();
        return lista.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(lista);
    }

    @Operation(summary = "Crear una notificación", description = "Registra una nueva notificación en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Notificación creada exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Notificacion.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos en la solicitud")
    })
    @PostMapping
    public ResponseEntity<Notificacion> guardar(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos de la notificación a crear", required = true)
            @RequestBody @Valid NotificacionDTO dto) {
        Notificacion n = new Notificacion(null, dto.getIdUsuario(), dto.getMensaje(), dto.getLeido());
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(n));
    }

    @Operation(summary = "Buscar notificación por ID", description = "Retorna una notificación según su identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notificación encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Notificacion.class))),
            @ApiResponse(responseCode = "404", description = "Notificación no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Notificacion> buscar(
            @Parameter(description = "ID de la notificación", required = true)
            @PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.findById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar una notificación", description = "Elimina una notificación por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Notificación eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Notificación no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(
            @Parameter(description = "ID de la notificación a eliminar", required = true)
            @PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
