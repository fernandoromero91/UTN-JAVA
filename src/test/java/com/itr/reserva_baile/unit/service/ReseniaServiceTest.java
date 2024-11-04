package com.itr.reserva_baile.unit.service;
 
import com.itr.reserva_baile.model.Resenia;
import com.itr.reserva_baile.repository.ReseniaRepository;
import com.itr.reserva_baile.service.ReseniaService;
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
 
public class ReseniaServiceTest {
 
    @Mock
    private ReseniaRepository reseniaRepository;
 
    @InjectMocks
    private ReseniaService reseniaService;
 
    private Resenia reseniaMock;
 
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        reseniaMock = new Resenia(1L, 1L, 1L, 1L, "5", 1L, "2024-03-20");
    }
 
    @Test
    void whenCreateResenia_thenReturnResenia() {
        when(reseniaRepository.save(any(Resenia.class))).thenReturn(reseniaMock);
 
        Resenia result = reseniaService.createResenia(reseniaMock);
 
        assertNotNull(result);
        assertEquals(reseniaMock.getIdResenia(), result.getIdResenia());
        verify(reseniaRepository).save(any(Resenia.class));
    }
 
    @Test
    void whenGetAllResenias_thenReturnList() {
        when(reseniaRepository.findAll()).thenReturn(Arrays.asList(reseniaMock));
 
        Iterable<Resenia> result = reseniaService.getAllResenias();
 
        assertNotNull(result);
        assertTrue(((Iterable<Resenia>) result).iterator().hasNext());
    }
 
    @Test
    void whenGetReseniaById_thenReturnResenia() {
        when(reseniaRepository.findById(1L)).thenReturn(Optional.of(reseniaMock));
 
        Optional<Resenia> result = reseniaService.getReseniaById(1L);
 
        assertTrue(result.isPresent());
        assertEquals(reseniaMock.getIdResenia(), result.get().getIdResenia());
        assertEquals(reseniaMock.getPuntuacion(), result.get().getPuntuacion());
    }
 
    @Test
    void whenUpdateResenia_thenReturnUpdatedResenia() {
        Resenia reseniaActualizada = new Resenia(1L, 1L, 1L, 1L, "4", 2L, "2024-03-21");
        when(reseniaRepository.findById(1L)).thenReturn(Optional.of(reseniaMock));
        when(reseniaRepository.save(any(Resenia.class))).thenReturn(reseniaActualizada);
 
        Resenia result = reseniaService.updateResenia(1L, reseniaActualizada);
 
        assertNotNull(result);
        assertEquals("4", result.getPuntuacion());
        assertEquals("2024-03-21", result.getFecha());
    }
 
    @Test
    void whenDeleteResenia_thenVerifyDelete() {
        doNothing().when(reseniaRepository).deleteById(1L);
       
        reseniaService.deleteResenia(1L);
       
        verify(reseniaRepository).deleteById(1L);
    }
 
    @Test
    void whenGetReseniaByUsuario_thenReturnList() {
        when(reseniaRepository.findByIdUsuario(1L)).thenReturn(Arrays.asList(reseniaMock));
 
        Iterable<Resenia> result = reseniaService.getReseniaByUsuario(1L);
 
        assertNotNull(result);
        assertTrue(((Iterable<Resenia>) result).iterator().hasNext());
    }
 
    @Test
    void whenGetReseniaByClase_thenReturnList() {
        when(reseniaRepository.findByIdClase(1L)).thenReturn(Arrays.asList(reseniaMock));
 
        Iterable<Resenia> result = reseniaService.getReseniaByClase(1L);
 
        assertNotNull(result);
        assertTrue(((Iterable<Resenia>) result).iterator().hasNext());
    }
 
    @Test
    void whenGetReseniaByEstudio_thenReturnList() {
        when(reseniaRepository.findByIdEstudio(1L)).thenReturn(Arrays.asList(reseniaMock));
 
        Iterable<Resenia> result = reseniaService.getReseniaByEstudio(1L);
 
        assertNotNull(result);
        assertTrue(((Iterable<Resenia>) result).iterator().hasNext());
    }
}