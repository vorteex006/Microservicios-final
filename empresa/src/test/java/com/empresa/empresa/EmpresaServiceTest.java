package com.empresa.empresa;

import com.empresa.empresa.client.PostulanteFeignClient;
import com.empresa.empresa.model.Empresa;
import com.empresa.empresa.repository.EmpresaRepository;
import com.empresa.empresa.service.EmpresaService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmpresaServiceTest {

    @InjectMocks
    private EmpresaService empresaService;

    @Mock
    private PostulanteFeignClient postulanteFeignClient;

    @Mock
    private EmpresaRepository empresaRepository;

    @Test
    public void testListarEmpresas() {
        Empresa mockEmpresa = new Empresa();
        mockEmpresa.setNombre("Tech Solutions S.A.");
        mockEmpresa.setSector("Tecnología");

        when(empresaRepository.findAll()).thenReturn(List.of(mockEmpresa));

        List<Empresa> empresas = empresaService.listar();

        assertNotNull(empresas);
        assertEquals(1, empresas.size());
        assertEquals("Tech Solutions S.A.", empresas.get(0).getNombre());
    }

    @Test
    public void testGuardarEmpresa() {
        Empresa nueva = new Empresa();
        nueva.setNombre("Tech Solutions S.A.");
        nueva.setSector("Tecnología");

        Empresa empresaGuardada = new Empresa();
        empresaGuardada.setId(1L);
        empresaGuardada.setNombre("Tech Solutions S.A.");

        when(empresaRepository.save(nueva)).thenReturn(empresaGuardada);

        Empresa resultado = empresaService.guardar(nueva);

        assertNotNull(resultado.getId());
        assertEquals("Tech Solutions S.A.", resultado.getNombre());
    }
}