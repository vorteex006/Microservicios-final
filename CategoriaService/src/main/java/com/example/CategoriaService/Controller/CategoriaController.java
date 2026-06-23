package com.example.CategoriaService.Controller;

import com.example.CategoriaService.Model.Categoria;
import com.example.CategoriaService.Service.CategoriaService;
import com.example.CategoriaService.dto.CategoriaDTO;
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
@RequestMapping("/api/v1/categorias")
@Tag(name = "Categorias", description = "Operaciones relacionadas con las categorías del mercado laboral")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @Operation(summary = "Listar todas las categorías", description = "Retorna la lista completa de categorías registradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Categoria.class))),
            @ApiResponse(responseCode = "204", description = "No hay categorías registradas")
    })
    @GetMapping
    public ResponseEntity<List<Categoria>> listar() {
        List<Categoria> lista = service.findAll();
        return lista.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(lista);
    }

    @Operation(summary = "Crear una categoría", description = "Registra una nueva categoría en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Categoría creada exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Categoria.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos en la solicitud")
    })
    @PostMapping
    public ResponseEntity<Categoria> guardar(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos de la categoría a crear", required = true)
            @RequestBody @Valid CategoriaDTO dto) {
        Categoria c = new Categoria(null, dto.getNombre());
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(c));
    }

    @Operation(summary = "Buscar categoría por ID", description = "Retorna una categoría según su identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoría encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Categoria.class))),
            @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscar(
            @Parameter(description = "ID de la categoría", required = true)
            @PathVariable Long id) {
        try { return ResponseEntity.ok(service.findById(id)); }
        catch (Exception e) { return ResponseEntity.notFound().build(); }
    }

    @Operation(summary = "Eliminar una categoría", description = "Elimina una categoría por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Categoría eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(
            @Parameter(description = "ID de la categoría a eliminar", required = true)
            @PathVariable Long id) {
        try { service.delete(id); return ResponseEntity.noContent().build(); }
        catch (Exception e) { return ResponseEntity.notFound().build(); }
    }
}
