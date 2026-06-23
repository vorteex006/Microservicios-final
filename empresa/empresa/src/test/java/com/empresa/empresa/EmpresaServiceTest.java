package com.empresa.empresa;

import com.empresa.empresa.model.Empresa;
import com.empresa.empresa.repository.EmpresaRepository;
import com.empresa.empresa.service.EmpresaService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
public class EmpresaServiceTest {

    @Autowired
    private EmpresaService empresaService;

    @MockBean
    private EmpresaRepository empresaRepository;

    @Test
    public void testListarEmpresas() {
        // 1. Preparamos el objeto de prueba
        Empresa mockEmpresa = new Empresa();
        mockEmpresa.setNombre("Tech Solutions S.A.");
        mockEmpresa.setSector("Tecnología");

        // 2. Simulamos que el repositorio devuelve esta lista
        when(empresaRepository.findAll()).thenReturn(List.of(mockEmpresa));

        // 3. Llamamos al servicio (asegúrate de que sea .listar())
        List<Empresa> empresas = empresaService.listar(); 

        // 4. Verificaciones
        assertNotNull(empresas);
        assertEquals(1, empresas.size());
        assertEquals("Tech Solutions S.A.", empresas.get(0).getNombre());
    }
}