package com.itr.reserva_baile.unit.service;

import com.itr.reserva_baile.model.ClaseDeBaile;
import com.itr.reserva_baile.repository.ClaseDeBaileRepository;
import com.itr.reserva_baile.service.ClaseDeBaileService;
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

public class ClaseDeBaileServiceTest {

    @Mock
    private ClaseDeBaileRepository claseDeBaileRepository;

    @InjectMocks
    private ClaseDeBaileService claseDeBaileService;

    private ClaseDeBaile claseDeBaileMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        claseDeBaileMock = new ClaseDeBaile(1L, "Salsa", "Juan Pérez", "Nivel Básico", 60, "10:00", 20, 25.0);
    }

    @Test
    void whenCreateClaseDeBaile_thenReturnClaseDeBaile() {
        when(claseDeBaileRepository.save(any(ClaseDeBaile.class))).thenReturn(claseDeBaileMock);
        
        ClaseDeBaile result = claseDeBaileService.createClase(claseDeBaileMock);
        
        assertNotNull(result);
        assertEquals(claseDeBaileMock.getId(), result.getId());
        verify(claseDeBaileRepository).save(any(ClaseDeBaile.class));
    }

    @Test
    void whenGetAllClasesDeBaile_thenReturnList() {
        when(claseDeBaileRepository.findAll()).thenReturn(Arrays.asList(claseDeBaileMock));
        
        Iterable<ClaseDeBaile> result = claseDeBaileService.getAllClases();
        
        assertNotNull(result);
        assertTrue(((Iterable<ClaseDeBaile>) result).iterator().hasNext());
    }

    @Test
    void whenGetClaseDeBaileById_thenReturnClaseDeBaile() {
        when(claseDeBaileRepository.findById(1L)).thenReturn(Optional.of(claseDeBaileMock));
        
        ClaseDeBaile result = claseDeBaileService.getClaseById(1L);
        
        assertNotNull(result);
        assertEquals(claseDeBaileMock.getId(), result.getId());
        assertEquals(claseDeBaileMock.getNombre(), result.getNombre());
    }

    @Test
    void whenUpdateClaseDeBaile_thenReturnUpdatedClaseDeBaile() {
        ClaseDeBaile claseActualizada = new ClaseDeBaile(1L, "Salsa", "Juan Pérez", "Nivel Básico", 60, "10:00", 20, 25.0);
        when(claseDeBaileRepository.findById(1L)).thenReturn(Optional.of(claseDeBaileMock));
        when(claseDeBaileRepository.save(any(ClaseDeBaile.class))).thenReturn(claseActualizada);
        
        ClaseDeBaile result = claseDeBaileService.updateClase(1L, claseActualizada);
        
        assertNotNull(result);
        assertEquals("Salsa", result.getNombre());
    }

    @Test
    void whenDeleteClaseDeBaile_thenVerifyDelete() {
        when(claseDeBaileRepository.findById(1L)).thenReturn(Optional.of(claseDeBaileMock));
        doNothing().when(claseDeBaileRepository).deleteById(1L);
        
        claseDeBaileService.deleteClase(1L);
        
        verify(claseDeBaileRepository).deleteById(1L);
    }
}