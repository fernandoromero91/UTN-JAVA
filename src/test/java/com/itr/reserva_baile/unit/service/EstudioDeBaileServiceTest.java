package com.itr.reserva_baile.unit.service;

import com.itr.reserva_baile.model.EstudioDeBaile;
import com.itr.reserva_baile.repository.EstudioDeBaileRepository;
import com.itr.reserva_baile.service.EstudioDeBaileService;
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

public class EstudioDeBaileServiceTest {

    @Mock
    private EstudioDeBaileRepository estudioDeBaileRepository;

    @InjectMocks
    private EstudioDeBaileService estudioDeBaileService;

    private EstudioDeBaile estudioMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        estudioMock = new EstudioDeBaile(1L, "Estudio de Baile A", "Calle Principal 123", 30, 25.0, true);
    }

    @Test
    void whenCreateEstudioDeBaile_thenReturnEstudioDeBaile() {
        when(estudioDeBaileRepository.save(any(EstudioDeBaile.class))).thenReturn(estudioMock);

        EstudioDeBaile result = estudioDeBaileService.createEstudio(estudioMock);

        assertNotNull(result);
        assertEquals(estudioMock.getId(), result.getId());
        verify(estudioDeBaileRepository).save(any(EstudioDeBaile.class));
    }

    @Test
    void whenGetAllEstudiosDeBaile_thenReturnList() {
        when(estudioDeBaileRepository.findAll()).thenReturn(Arrays.asList(estudioMock));

        Iterable<EstudioDeBaile> result = estudioDeBaileService.getAllEstudios();

        assertNotNull(result);
        assertTrue(((Iterable<EstudioDeBaile>) result).iterator().hasNext());
    }

    @Test
    void whenGetEstudioDeBaileById_thenReturnEstudioDeBaile() {
        when(estudioDeBaileRepository.findById(1L)).thenReturn(Optional.of(estudioMock));

        EstudioDeBaile result = estudioDeBaileService.getEstudioById(1L);

        assertNotNull(result);
        assertEquals(estudioMock.getId(), result.getId());
        assertEquals(estudioMock.getNombre(), result.getNombre());
    }

    @Test
    void whenUpdateEstudioDeBaile_thenReturnUpdatedEstudioDeBaile() {
        EstudioDeBaile estudioActualizado = new EstudioDeBaile(1L, "Estudio de Baile A Actualizado", "Calle Principal 123", 30, 25.0, true);
        when(estudioDeBaileRepository.findById(1L)).thenReturn(Optional.of(estudioMock));
        when(estudioDeBaileRepository.save(any(EstudioDeBaile.class))).thenReturn(estudioActualizado);

        EstudioDeBaile result = estudioDeBaileService.updateEstudio(1L, estudioActualizado);

        assertNotNull(result);
        assertEquals("Estudio de Baile A Actualizado", result.getNombre());
    }

    @Test
    void whenDeleteEstudioDeBaile_thenVerifyDelete() {
        when(estudioDeBaileRepository.findById(1L)).thenReturn(Optional.of(estudioMock));
        doNothing().when(estudioDeBaileRepository).deleteById(1L);

        estudioDeBaileService.deleteEstudio(1L);

        verify(estudioDeBaileRepository).deleteById(1L);
    }
}