package com.itr.reserva_baile.integration.controller;

import com.itr.reserva_baile.model.Usuario;
import com.itr.reserva_baile.repository.UsuarioRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;

@SpringBootTest
@AutoConfigureMockMvc
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UsuarioRepository usuarioRepository;


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
}
