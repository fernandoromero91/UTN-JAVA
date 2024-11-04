package com.itr.reserva_baile.service;

import com.itr.reserva_baile.model.Paquete;
import com.itr.reserva_baile.model.ClaseDeBaile;
import com.itr.reserva_baile.repository.PaqueteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PaqueteServiceTest {

    @Mock
    private PaqueteRepository paqueteRepository;

    @InjectMocks
    private PaqueteService paqueteService;

    private Paquete paqueteEjemplo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        paqueteEjemplo = new Paquete(
            1L,
            "Paquete Básico",
            "Descripción del paquete básico",
            new BigDecimal("100.00"),
            30,
            Arrays.asList(new ClaseDeBaile())
        );
    }

    @Test
    void testGetPaqueteById() {
        when(paqueteRepository.findById(1L)).thenReturn(Optional.of(paqueteEjemplo));

        Optional<Paquete> result = paqueteService.getPaqueteById(1L);

        assertTrue(result.isPresent());
        assertEquals("Paquete Básico", result.get().getNombrePaquete());
        assertEquals(new BigDecimal("100.00"), result.get().getPrecio());
    }

    @Test
    void testCreatePaquete() {
        when(paqueteRepository.save(any(Paquete.class))).thenReturn(paqueteEjemplo);

        Paquete result = paqueteService.createPaquete(paqueteEjemplo);

        assertNotNull(result);
        assertEquals("Paquete Básico", result.getNombrePaquete());
        assertEquals(new BigDecimal("100.00"), result.getPrecio());
    }

    @Test
    void testGetAllPaquetes() {
        List<Paquete> paquetes = Arrays.asList(
            paqueteEjemplo,
            new Paquete(2L, "Paquete Premium", "Descripción premium", new BigDecimal("200.00"), 60, Arrays.asList(new ClaseDeBaile()))
        );
        when(paqueteRepository.findAll()).thenReturn(paquetes);

        Iterable<Paquete> result = paqueteService.getAllPaquetes();

        assertEquals(2, ((List<Paquete>) result).size());
    }

    @Test
    void testUpdatePaquete() {
        Paquete paqueteActualizado = new Paquete(
            1L,
            "Paquete Básico Actualizado",
            "Nueva descripción",
            new BigDecimal("150.00"),
            45,
            Arrays.asList(new ClaseDeBaile())
        );

        when(paqueteRepository.findById(1L)).thenReturn(Optional.of(paqueteEjemplo));
        when(paqueteRepository.save(any(Paquete.class))).thenReturn(paqueteActualizado);

        Paquete result = paqueteService.updatePaquete(1L, paqueteActualizado);

        assertEquals("Paquete Básico Actualizado", result.getNombrePaquete());
        assertEquals(new BigDecimal("150.00"), result.getPrecio());
    }

    @Test
    void testDeletePaquete() {
        doNothing().when(paqueteRepository).deleteById(1L);
        paqueteService.deletePaquete(1L);
        verify(paqueteRepository, times(1)).deleteById(1L);
    }
}