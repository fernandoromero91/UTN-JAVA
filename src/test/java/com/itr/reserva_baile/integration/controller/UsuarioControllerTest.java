package com.itr.reserva_baile.integration.controller;

import com.itr.reserva_baile.model.Usuario;
import com.itr.reserva_baile.repository.UsuarioRepository;
import com.itr.reserva_baile.repository.ReservaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    @BeforeEach
    void setUp() {
        reservaRepository.deleteAll();
        usuarioRepository.deleteAll();
    }


    @Test
    void testGetAllUsuarios() throws Exception {
        // Crear un usuario en la base de datos de prueba
        Usuario usuario = new Usuario(null, "Usuario Test", "email@test.com", "12345678", "USER");
        usuarioRepository.save(usuario);

        // Hacer la solicitud GET y verificar el resultado
        mockMvc.perform(get("/usuarios")
                .with(httpBasic("admin", "admin"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre", is("Usuario Test")));
    }

    @Test
    void testCreateUsuario() throws Exception {
        String usuarioJson = "{ \"nombre\": \"Nuevo Usuario\", \"email\": \"nuevo@test.com\", \"telefono\": \"87654321\", \"rol\": \"USER\" }";

        mockMvc.perform(post("/usuarios")
                .with(httpBasic("admin", "admin"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(usuarioJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre", is("Nuevo Usuario")))
                .andExpect(jsonPath("$.email", is("nuevo@test.com")));
    }

    @Test
    void testGetUsuarioById() throws Exception {
        Usuario usuario = new Usuario(null, "Usuario Test", "email@test.com", "12345678", "USER");
        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        mockMvc.perform(get("/usuarios/{id}", usuarioGuardado.getId())
                .with(httpBasic("admin", "admin")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", is("Usuario Test")))
                .andExpect(jsonPath("$.email", is("email@test.com")));
    }

    @Test
    void testUpdateUsuario() throws Exception {
        Usuario usuario = new Usuario(null, "Usuario Original", "original@test.com", "12345678", "USER");
        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        String usuarioActualizadoJson = "{ \"nombre\": \"Usuario Actualizado\", \"email\": \"actualizado@test.com\", \"telefono\": \"87654321\", \"rol\": \"USER\" }";

        mockMvc.perform(put("/usuarios/{id}", usuarioGuardado.getId())
                .with(httpBasic("admin", "admin"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(usuarioActualizadoJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", is("Usuario Actualizado")))
                .andExpect(jsonPath("$.email", is("actualizado@test.com")));
    }

    @Test
    void testDeleteUsuario() throws Exception {
        Usuario usuario = new Usuario(null, "Usuario Test", "email@test.com", "12345678", "USER");
        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        mockMvc.perform(delete("/usuarios/{id}", usuarioGuardado.getId())
                .with(httpBasic("admin", "admin")))
                .andExpect(status().isOk());

        mockMvc.perform(get("/usuarios/{id}", usuarioGuardado.getId())
                .with(httpBasic("admin", "admin")))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateUsuarioWithInvalidData() throws Exception {
        String usuarioInvalidoJson = "{ \"nombre\": \"\", \"email\": \"invalido\", \"telefono\": \"123\", \"rol\": \"INVALID_ROLE\" }";

        mockMvc.perform(post("/usuarios")
                .with(httpBasic("admin", "admin"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(usuarioInvalidoJson))
                .andExpect(status().isBadRequest());
    }
}
