package com.itr.reserva_baile.unit.service;

import com.itr.reserva_baile.model.Pago;
import com.itr.reserva_baile.repository.PagoRepository;
import com.itr.reserva_baile.service.PagoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PagoServiceTest {

    @Mock
    private PagoRepository pagoRepository;

    @InjectMocks
    private PagoService pagoService;

    private Pago pagoMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pagoMock = new Pago(1L, 1L, null, 150.0, "2023-01-01", "Tarjeta de crédito");
    }

    @Test
    void whenCreatePago_thenReturnPago() {
        when(pagoRepository.save(any(Pago.class))).thenReturn(pagoMock);

        Pago result = pagoService.createPago(pagoMock);

        assertNotNull(result);
        assertEquals(pagoMock.getMonto(), result.getMonto());
        verify(pagoRepository).save(any(Pago.class));
    }

    @Test
    void whenGetAllPagos_thenReturnList() {
        when(pagoRepository.findAll()).thenReturn(java.util.List.of(pagoMock));

        Iterable<Pago> result = pagoService.getAllPagos();

        assertNotNull(result);
        assertTrue(result.iterator().hasNext());
    }

    @Test
    void whenGetPagoById_thenReturnPago() {
        when(pagoRepository.findById(1L)).thenReturn(Optional.of(pagoMock));

        Optional<Pago> result = pagoService.getPagoById(1L);

        assertTrue(result.isPresent());
        assertEquals(pagoMock.getId(), result.get().getId());
    }

    @Test
    void whenUpdatePago_thenReturnUpdatedPago() {
        Pago updatedPago = new Pago(1L, 1L, null, 200.0, "2023-01-01", "Transferencia");
        when(pagoRepository.findById(1L)).thenReturn(Optional.of(pagoMock));
        when(pagoRepository.save(any(Pago.class))).thenReturn(updatedPago);

        Pago result = pagoService.updatePago(1L, updatedPago);

        assertNotNull(result);
        assertEquals(200.0, result.getMonto());
        assertEquals("Transferencia", result.getMetodoPago());
    }

    @Test
    void whenDeletePago_thenVerifyDelete() {
        when(pagoRepository.findById(1L)).thenReturn(Optional.of(pagoMock));
        doNothing().when(pagoRepository).deleteById(1L);

        pagoService.deletePago(1L);

        verify(pagoRepository).deleteById(1L);
    }
}
