package com.itr.reserva_baile.integration.controller;

import com.itr.reserva_baile.model.Instructor;
import com.itr.reserva_baile.repository.InstructorRepository;
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
public class InstructorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private InstructorRepository instructorRepository;

    @BeforeEach
    void setup() {
        instructorRepository.deleteAll();
    }

    @Test
    void testGetAllInstructors() throws Exception {
        Instructor instructor = new Instructor(null, "Carlos", "Salsa", 5, "contacto@ejemplo.com");
        instructorRepository.save(instructor);

        mockMvc.perform(get("/instructores")
                        .with(httpBasic("admin", "admin"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre", is("Carlos")))
                .andExpect(jsonPath("$[0].especialidad", is("Salsa")));
    }

    @Test
    void testCreateInstructor() throws Exception {
        String instructorJson = "{ \"nombre\": \"Ana\", \"especialidad\": \"Bachata\", \"experiencia\": 3, \"contacto\": \"ana@ejemplo.com\" }";

        mockMvc.perform(post("/instructores")
                        .with(httpBasic("admin", "admin"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(instructorJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre", is("Ana")))
                .andExpect(jsonPath("$.especialidad", is("Bachata")));
    }

    @Test
    void testUpdateInstructor() throws Exception {
        Instructor instructor = new Instructor(null, "Luis", "Merengue", 4, "luis@ejemplo.com");
        Instructor savedInstructor = instructorRepository.save(instructor);

        String updatedInstructorJson = "{ \"nombre\": \"Luis Modificado\", \"especialidad\": \"Merengue\", \"experiencia\": 5, \"contacto\": \"luis_mod@ejemplo.com\" }";

        mockMvc.perform(put("/instructores/" + savedInstructor.getId())
                        .with(httpBasic("admin", "admin"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedInstructorJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", is("Luis Modificado")));
    }

    @Test
    void testDeleteInstructor() throws Exception {
        Instructor instructor = new Instructor(null, "Pedro", "Hip-hop", 2, "pedro@ejemplo.com");
        Instructor savedInstructor = instructorRepository.save(instructor);

        mockMvc.perform(delete("/instructores/" + savedInstructor.getId())
                        .with(httpBasic("admin", "admin")))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/instructores/" + savedInstructor.getId())
                        .with(httpBasic("admin", "admin")))
                .andExpect(status().isNotFound());
    }
}

