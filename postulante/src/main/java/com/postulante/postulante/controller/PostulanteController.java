package com.postulante.postulante.controller;

import com.postulante.postulante.dto.PostulanteDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import com.postulante.postulante.model.Postulante;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.postulante.postulante.service.PostulanteService;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/postulantes")
@RequiredArgsConstructor
@Tag(name = "Postulantes", description = "Operaciones relacionadas con los postulantes")
public class PostulanteController {

    private final PostulanteService service;

    @GetMapping
    @Operation(summary = "Listar postulantes", description = "Retorna todos los postulantes registrados")
    public List<Postulante> get() {
        return service.listar();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar postulante por id", description = "Retorna un postulante segun su id")
    public Postulante buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @GetMapping("/buscar")
    @Operation(summary = "Buscar postulante por correo", description = "Retorna los postulantes que coincidan con el correo exacto")
    public List<Postulante> buscarPorEmail(@RequestParam(name = "correo") String correo) {
        return service.buscarPorEmail(correo);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Guardar un postulante", description = "Crea y registra un nuevo postulante en el sistema")
    public Postulante save(@Valid @RequestBody PostulanteDTO dto) {
        Postulante p = new Postulante(null, dto.getNombre(), dto.getApellido(), dto.getCorreo(), dto.getHabilidad());
        return service.guardar(p);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Eliminar un postulante", description = "Elimina un postulante existente segun su id")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
