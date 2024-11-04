package com.itr.reserva_baile.service;

import com.itr.reserva_baile.model.Notificacion;
import com.itr.reserva_baile.repository.NotificacionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class NotificacionServiceTest {

    @Mock
    private NotificacionRepository notificacionRepository;

    @InjectMocks
    private NotificacionService notificacionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetNotificacionById() {
        // Arrange
        Notificacion notificacion = new Notificacion(1L, "Mensaje test", "PENDIENTE", 1L);
        when(notificacionRepository.findById(1L)).thenReturn(Optional.of(notificacion));

        // Act
        Optional<Notificacion> result = notificacionService.getNotificacionById(1L);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Mensaje test", result.get().getMensaje());
        assertEquals("PENDIENTE", result.get().getEstado());
    }

    @Test
    void testCreateNotificacion() {
        // Arrange
        Notificacion notificacion = new Notificacion(null, "Nueva notificación", "PENDIENTE", 1L);
        Notificacion notificacionGuardada = new Notificacion(1L, "Nueva notificación", "PENDIENTE", 1L);
        when(notificacionRepository.save(notificacion)).thenReturn(notificacionGuardada);

        // Act
        Notificacion result = notificacionService.createNotificacion(notificacion);

        // Assert
        assertNotNull(result.getId());
        assertEquals("Nueva notificación", result.getMensaje());
        assertEquals("PENDIENTE", result.getEstado());
    }

    @Test
    void testGetAllNotificaciones() {
        // Arrange
        List<Notificacion> notificaciones = Arrays.asList(
            new Notificacion(1L, "Notificación 1", "PENDIENTE", 1L),
            new Notificacion(2L, "Notificación 2", "LEIDA", 1L)
        );
        when(notificacionRepository.findAll()).thenReturn(notificaciones);

        // Act
        Iterable<Notificacion> result = notificacionService.getAllNotificaciones();

        // Assert
        assertEquals(2, ((List<Notificacion>) result).size());
    }

    @Test
    void testUpdateNotificacion() {
        // Arrange
        Long id = 1L;
        Notificacion notificacionExistente = new Notificacion(id, "Mensaje original", "PENDIENTE", 1L);
        Notificacion notificacionActualizada = new Notificacion(id, "Mensaje actualizado", "LEIDA", 1L);
        
        when(notificacionRepository.findById(id)).thenReturn(Optional.of(notificacionExistente));
        when(notificacionRepository.save(any(Notificacion.class))).thenReturn(notificacionActualizada);

        // Act
        Notificacion result = notificacionService.updateNotificacion(id, notificacionActualizada);

        // Assert
        assertEquals("Mensaje actualizado", result.getMensaje());
        assertEquals("LEIDA", result.getEstado());
    }

    @Test
    void testGetNotificacionesByEstado() {
        // Arrange
        List<Notificacion> notificaciones = Arrays.asList(
            new Notificacion(1L, "Notificación 1", "PENDIENTE", 1L),
            new Notificacion(2L, "Notificación 2", "PENDIENTE", 2L)
        );
        when(notificacionRepository.findByEstado("PENDIENTE")).thenReturn(notificaciones);

        // Act
        Iterable<Notificacion> result = notificacionService.getNotificacionesByEstado("PENDIENTE");

        // Assert
        assertEquals(2, ((List<Notificacion>) result).size());
        assertTrue(((List<Notificacion>) result).stream()
                .allMatch(n -> n.getEstado().equals("PENDIENTE")));
    }

    @Test
    void testGetNotificacionesByDestinatario() {
        // Arrange
        Long destinatarioId = 1L;
        List<Notificacion> notificaciones = Arrays.asList(
            new Notificacion(1L, "Notificación 1", "PENDIENTE", destinatarioId),
            new Notificacion(2L, "Notificación 2", "LEIDA", destinatarioId)
        );
        when(notificacionRepository.findByDestinatarioId(destinatarioId)).thenReturn(notificaciones);

        // Act
        Iterable<Notificacion> result = notificacionService.getNotificacionesByDestinatario(destinatarioId);

        // Assert
        assertEquals(2, ((List<Notificacion>) result).size());
        assertTrue(((List<Notificacion>) result).stream()
                .allMatch(n -> n.getDestinatarioId().equals(destinatarioId)));
    }
}