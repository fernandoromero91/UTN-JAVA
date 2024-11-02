package com.itr.reserva_baile.unit.service;

import com.itr.reserva_baile.model.Usuario;
import com.itr.reserva_baile.repository.UsuarioRepository;
import com.itr.reserva_baile.service.UsuarioService;
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

public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    private Usuario usuarioMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        usuarioMock = new Usuario(1L, "Juan", "juan@email.com", "12345678", "USER");

    }

    @Test
    void whenCreateUsuario_thenReturnUsuario() {
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuarioMock);
        
        Usuario result = usuarioService.createUsuario(usuarioMock);
        
        assertNotNull(result);
        assertEquals(usuarioMock.getId(), result.getId());
        verify(usuarioRepository).save(any(Usuario.class));
    }

    @Test
    void whenGetAllUsuarios_thenReturnList() {
        when(usuarioRepository.findAll()).thenReturn(Arrays.asList(usuarioMock));
        
        Iterable<Usuario> result = usuarioService.getAllUsuarios();
        
        assertNotNull(result);
        assertTrue(((Iterable<Usuario>) result).iterator().hasNext());
    }

    @Test
    void whenGetUsuarioById_thenReturnUsuario() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuarioMock));
        
        Optional<Usuario> result = usuarioService.getUsuarioById(1L);
        
        assertTrue(result.isPresent());
        assertEquals(usuarioMock.getId(), result.get().getId());
        assertEquals(usuarioMock.getNombre(), result.get().getNombre());
    }

    @Test
    void whenUpdateUsuario_thenReturnUpdatedUsuario() {
        Usuario usuarioActualizado = new Usuario(1L, "Juan Actualizado", "juan@email.com", "12345678", "USER");
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuarioMock));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuarioActualizado);
        
        Usuario result = usuarioService.updateUsuario(1L, usuarioActualizado);
        
        assertNotNull(result);
        assertEquals("Juan Actualizado", result.getNombre());
    }

    @Test
    void whenDeleteUsuario_thenVerifyDelete() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuarioMock));
        doNothing().when(usuarioRepository).deleteById(1L);
        
        usuarioService.deleteUsuario(1L);
        
        verify(usuarioRepository).deleteById(1L);
    }
}