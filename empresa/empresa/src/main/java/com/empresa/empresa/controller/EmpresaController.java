package com.empresa.empresa.controller;

import com.empresa.empresa.dto.EmpresaDTO;
import com.empresa.empresa.model.Empresa;
import com.empresa.empresa.service.EmpresaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/empresas")
@RequiredArgsConstructor
public class EmpresaController {
    private final EmpresaService service;

    @GetMapping
    public List<Empresa> get() { return service.listar(); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Empresa save(@Valid @RequestBody EmpresaDTO dto) {
        Empresa e = new Empresa(null, dto.getNombre(), dto.getSector());
        return service.guardar(e);
    }

    @GetMapping("/prueba-feign/{id}")
    public String probarComunicacion(@PathVariable("id") Long id) {
        service.buscarDatosDeUnPostulante(id); 
        return "¡Llamada realizada! Revisa la consola negra de VS Code para ver los datos.";
    }
    
}