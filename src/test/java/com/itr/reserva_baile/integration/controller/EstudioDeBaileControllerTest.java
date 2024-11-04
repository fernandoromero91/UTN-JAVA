package com.itr.reserva_baile.integration.controller;

import com.itr.reserva_baile.model.EstudioDeBaile;
import com.itr.reserva_baile.repository.EstudioDeBaileRepository;
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
public class EstudioDeBaileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EstudioDeBaileRepository estudioDeBaileRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    @BeforeEach
    void setUp() {
        reservaRepository.deleteAll();
        estudioDeBaileRepository.deleteAll();
    }

    @Test
    void testGetAllEstudios() throws Exception {
        EstudioDeBaile estudio = new EstudioDeBaile(null, "Estudio A", "Calle Principal 123", 30, 25.0, true);
        estudioDeBaileRepository.save(estudio);

        mockMvc.perform(get("/estudios")
                .with(httpBasic("admin", "admin"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre", is("Estudio A")))
                .andExpect(jsonPath("$[0].ubicacion", is("Calle Principal 123")));
    }

    @Test
    void testCreateEstudio() throws Exception {
        String estudioJson = "{ \"nombre\": \"Nuevo Estudio\", \"ubicacion\": \"Calle Secundaria 456\", \"capacidad\": 25, \"precioHora\": 30.0, \"disponibilidad\": true }";

        mockMvc.perform(post("/estudios")
                .with(httpBasic("admin", "admin"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(estudioJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre", is("Nuevo Estudio")))
                .andExpect(jsonPath("$.ubicacion", is("Calle Secundaria 456")));
    }

    @Test
    void testGetEstudioById() throws Exception {
        EstudioDeBaile estudio = new EstudioDeBaile(null, "Estudio A", "Calle Principal 123", 30, 25.0, true);
        EstudioDeBaile estudioGuardado = estudioDeBaileRepository.save(estudio);

        mockMvc.perform(get("/estudios/{id}", estudioGuardado.getId())
                .with(httpBasic("admin", "admin")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", is("Estudio A")))
                .andExpect(jsonPath("$.ubicacion", is("Calle Principal 123")));
    }

    @Test
    void testUpdateEstudio() throws Exception {
        EstudioDeBaile estudio = new EstudioDeBaile(null, "Estudio A", "Calle Principal 123", 30, 25.0, true);
        EstudioDeBaile estudioGuardado = estudioDeBaileRepository.save(estudio);

        String estudioActualizadoJson = "{ \"nombre\": \"Estudio A Renovado\", \"ubicacion\": \"Calle Principal 123\", \"capacidad\": 35, \"precioHora\": 30.0, \"disponibilidad\": true }";

        mockMvc.perform(put("/estudios/{id}", estudioGuardado.getId())
                .with(httpBasic("admin", "admin"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(estudioActualizadoJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", is("Estudio A Renovado")))
                .andExpect(jsonPath("$.capacidad", is(35)));
    }

    @Test
    void testDeleteEstudio() throws Exception {
        EstudioDeBaile estudio = new EstudioDeBaile(null, "Estudio A", "Calle Principal 123", 30, 25.0, true);
        EstudioDeBaile estudioGuardado = estudioDeBaileRepository.save(estudio);

        mockMvc.perform(delete("/estudios/{id}", estudioGuardado.getId())
                .with(httpBasic("admin", "admin")))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/estudios/{id}", estudioGuardado.getId())
                .with(httpBasic("admin", "admin")))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateEstudioWithInvalidData() throws Exception {
        String estudioInvalidoJson = "{ \"nombre\": \"\", \"ubicacion\": \"\", \"capacidad\": -1, \"precioHora\": -1.0, \"disponibilidad\": null }";

        mockMvc.perform(post("/estudios")
                .with(httpBasic("admin", "admin"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(estudioInvalidoJson))
                .andExpect(status().isBadRequest());
    }
}