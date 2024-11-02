package com.itr.reserva_baile.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itr.reserva_baile.model.Paquete;
import com.itr.reserva_baile.service.PaqueteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PaqueteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaqueteService paqueteService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllPaquetes() throws Exception {
        Paquete paquete = new Paquete(1L, "Paquete Test", "Descripción", new BigDecimal("100.00"), 30, Arrays.asList());
        when(paqueteService.getAllPaquetes()).thenReturn(Arrays.asList(paquete));

        mockMvc.perform(get("/paquetes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombrePaquete").value("Paquete Test"))
                .andExpect(jsonPath("$[0].precio").value("100.0"))
                .andExpect(jsonPath("$[0].precio").exists());
    }

    @Test
    void testCreatePaquete() throws Exception {
        Paquete paquete = new Paquete(null, "Nuevo Paquete", "Descripción", new BigDecimal("100.00"), 30, Arrays.asList());
        Paquete paqueteGuardado = new Paquete(1L, "Nuevo Paquete", "Descripción", new BigDecimal("100.00"), 30, Arrays.asList());
        
        when(paqueteService.createPaquete(any(Paquete.class))).thenReturn(paqueteGuardado);

        mockMvc.perform(post("/paquetes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(paquete)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombrePaquete").value("Nuevo Paquete"));
    }

    @Test
    void testGetPaqueteById() throws Exception {
        Paquete paquete = new Paquete(1L, "Paquete Test", "Descripción", new BigDecimal("100.00"), 30, Arrays.asList());
        when(paqueteService.getPaqueteById(1L)).thenReturn(Optional.of(paquete));

        mockMvc.perform(get("/paquetes/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombrePaquete").value("Paquete Test"));
    }

    @Test
    void testUpdatePaquete() throws Exception {
        Paquete paquete = new Paquete(1L, "Paquete Actualizado", "Nueva descripción", new BigDecimal("150.00"), 45, Arrays.asList());
        when(paqueteService.updatePaquete(eq(1L), any(Paquete.class))).thenReturn(paquete);

        mockMvc.perform(put("/paquetes/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(paquete)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombrePaquete").value("Paquete Actualizado"))
                .andExpect(jsonPath("$.precio").value("150.0"))
                .andExpect(jsonPath("$.precio").exists());
    }

    @Test
    void testDeletePaquete() throws Exception {
        mockMvc.perform(delete("/paquetes/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}