package com.itr.reserva_baile.integration.controller;

import com.itr.reserva_baile.model.Pago;
import com.itr.reserva_baile.repository.PagoRepository;
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
public class PagoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PagoRepository pagoRepository;

    @BeforeEach
    void setup() {
        pagoRepository.deleteAll();
    }

    @Test
    void testCreatePago() throws Exception {
        String pagoJson = "{ \"usuarioId\": 1, \"monto\": 100.0, \"fecha\": \"2023-01-01\", \"metodoPago\": \"Tarjeta de crédito\" }";

        mockMvc.perform(post("/pagos")
                        .with(httpBasic("admin", "admin"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(pagoJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.monto", is(100.0)))
                .andExpect(jsonPath("$.metodoPago", is("Tarjeta de crédito")));
    }

    @Test
    void testGetAllPagos() throws Exception {
        Pago pago = new Pago(null, 1L, null, 100.0, "2023-01-01", "Tarjeta de crédito");
        pagoRepository.save(pago);

        mockMvc.perform(get("/pagos")
                        .with(httpBasic("admin", "admin"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].monto", is(100.0)))
                .andExpect(jsonPath("$[0].metodoPago", is("Tarjeta de crédito")));
    }
}
