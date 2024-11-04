package com.itr.reserva_baile.unit.service;

import com.itr.reserva_baile.model.Reserva;
import com.itr.reserva_baile.repository.ReservaRepository;
import com.itr.reserva_baile.service.ReservaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ReservaServiceTest {

    @Mock
    private ReservaRepository reservaRepository;

    @InjectMocks
    private ReservaService reservaService;

    private Reserva reservaMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        reservaMock = new Reserva(1L, 1L, 1L, 1L, "2024-03-20", "10:00", 60, "confirmada");
    }

    @Test
    void whenCreateReserva_thenReturnReserva() {
        when(reservaRepository.save(any(Reserva.class))).thenReturn(reservaMock);

        Reserva result = reservaService.createReserva(reservaMock);

        assertNotNull(result);
        assertEquals(reservaMock.getId(), result.getId());
        verify(reservaRepository).save(any(Reserva.class));
    }

    @Test
    void whenGetAllReservas_thenReturnList() {
        when(reservaRepository.findAll()).thenReturn(Arrays.asList(reservaMock));

        Iterable<Reserva> result = reservaService.getAllReservas();

        assertNotNull(result);
        assertTrue(((Iterable<Reserva>) result).iterator().hasNext());
    }

    @Test
    void whenGetReservaById_thenReturnReserva() {
        when(reservaRepository.findById(1L)).thenReturn(Optional.of(reservaMock));

        Reserva result = reservaService.getReservaById(1L);

        assertNotNull(result);
        assertEquals(reservaMock.getId(), result.getId());
        assertEquals(reservaMock.getFecha(), result.getFecha());
    }

    @Test
    void whenUpdateReserva_thenReturnUpdatedReserva() {
        Reserva reservaActualizada = new Reserva(1L, 1L, 1L, 1L, "2024-03-21", "11:00", 90, "pendiente");
        when(reservaRepository.findById(1L)).thenReturn(Optional.of(reservaMock));
        when(reservaRepository.save(any(Reserva.class))).thenReturn(reservaActualizada);

        Reserva result = reservaService.updateReserva(1L, reservaActualizada);

        assertNotNull(result);
        assertEquals("2024-03-21", result.getFecha());
        assertEquals("pendiente", result.getEstado());
    }

    @Test
    void whenDeleteReserva_thenVerifyDelete() {
        when(reservaRepository.findById(1L)).thenReturn(Optional.of(reservaMock));
        doNothing().when(reservaRepository).deleteById(1L);

        reservaService.deleteReserva(1L);

        verify(reservaRepository).deleteById(1L);
    }
}