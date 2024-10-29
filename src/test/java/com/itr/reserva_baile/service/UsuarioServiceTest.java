package com.itr.reserva_baile.service;

import com.itr.reserva_baile.model.Usuario;
import com.itr.reserva_baile.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUsuarioById() {
        Usuario usuario = new Usuario(1L, "Nombre Test", "email@test.com", "12345678", "USER");
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        Optional<Usuario> result = usuarioService.getUsuarioById(1L);
        assertTrue(result.isPresent());
        assertEquals("Nombre Test", result.get().getNombre());
    }

    @Test
    void testCreateUsuario() {
        Usuario usuario = new Usuario(null, "Nombre Nuevo", "nuevo@test.com", "87654321", "USER");
        when(usuarioRepository.save(usuario)).thenReturn(new Usuario(1L, "Nombre Nuevo", "nuevo@test.com", "87654321", "USER"));

        Usuario result = usuarioService.createUsuario(usuario);
        assertNotNull(result.getId());
        assertEquals("Nombre Nuevo", result.getNombre());
    }
}
