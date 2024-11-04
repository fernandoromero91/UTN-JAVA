package com.itr.reserva_baile.integration.controller;

import com.itr.reserva_baile.model.ClaseDeBaile;
import com.itr.reserva_baile.repository.ClaseDeBaileRepository;
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
public class ClaseDeBaileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClaseDeBaileRepository claseDeBaileRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    @BeforeEach
    void setUp() {
        reservaRepository.deleteAll();
        claseDeBaileRepository.deleteAll();
    }

    @Test
    void testGetAllClases() throws Exception {
        ClaseDeBaile clase = new ClaseDeBaile(null, "Salsa", "Juan Pérez", "Nivel Básico", 60, "10:00", 20, 25.0);
        claseDeBaileRepository.save(clase);

        mockMvc.perform(get("/clases")
                .with(httpBasic("admin", "admin"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre", is("Salsa")))
                .andExpect(jsonPath("$[0].instructor", is("Juan Pérez")));
    }

    @Test
    void testCreateClase() throws Exception {
        String claseJson = "{ \"nombre\": \"Tango\", \"instructor\": \"María López\", \"nivelDificultad\": \"Intermedio\", \"duracion\": 90, \"horario\": \"11:00\", \"capacidad\": 15, \"precio\": 30.0 }";

        mockMvc.perform(post("/clases")
                .with(httpBasic("admin", "admin"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(claseJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre", is("Tango")))
                .andExpect(jsonPath("$.instructor", is("María López")));
    }

    @Test
    void testGetClaseById() throws Exception {
        ClaseDeBaile clase = new ClaseDeBaile(null, "Salsa", "Juan Pérez", "Nivel Básico", 60, "10:00", 20, 25.0);
        ClaseDeBaile claseGuardada = claseDeBaileRepository.save(clase);

        mockMvc.perform(get("/clases/{id}", claseGuardada.getId())
                .with(httpBasic("admin", "admin")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", is("Salsa")))
                .andExpect(jsonPath("$.instructor", is("Juan Pérez")));
    }

    @Test
    void testUpdateClase() throws Exception {
        ClaseDeBaile clase = new ClaseDeBaile(null, "Salsa", "Juan Pérez", "Nivel Básico", 60, "10:00", 20, 25.0);
        ClaseDeBaile claseGuardada = claseDeBaileRepository.save(clase);

        String claseActualizadaJson = "{ \"nombre\": \"Salsa Avanzada\", \"instructor\": \"Juan Pérez\", \"nivelDificultad\": \"Avanzado\", \"duracion\": 90, \"horario\": \"11:00\", \"capacidad\": 15, \"precio\": 35.0 }";

        mockMvc.perform(put("/clases/{id}", claseGuardada.getId())
                .with(httpBasic("admin", "admin"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(claseActualizadaJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", is("Salsa Avanzada")))
                .andExpect(jsonPath("$.nivelDificultad", is("Avanzado")));
    }

    @Test
    void testDeleteClase() throws Exception {
        ClaseDeBaile clase = new ClaseDeBaile(null, "Salsa", "Juan Pérez", "Nivel Básico", 60, "10:00", 20, 25.0);
        ClaseDeBaile claseGuardada = claseDeBaileRepository.save(clase);

        mockMvc.perform(delete("/clases/{id}", claseGuardada.getId())
                .with(httpBasic("admin", "admin")))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/clases/{id}", claseGuardada.getId())
                .with(httpBasic("admin", "admin")))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateClaseWithInvalidData() throws Exception {
        String claseInvalidaJson = "{ \"nombre\": \"\", \"instructor\": \"\", \"nivelDificultad\": \"\", \"duracion\": -1, \"horario\": \"\", \"capacidad\": -1, \"precio\": -1.0 }";

        mockMvc.perform(post("/clases")
                .with(httpBasic("admin", "admin"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(claseInvalidaJson))
                .andExpect(status().isBadRequest());
    }
}