package com.itr.reserva_baile.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itr.reserva_baile.model.Notificacion;
import com.itr.reserva_baile.service.NotificacionService;
import com.itr.reserva_baile.config.TestSecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.BeanUtils;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Import(TestSecurityConfig.class)
public class NotificacionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotificacionService notificacionService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllNotificaciones() throws Exception {
        Notificacion notificacion = new Notificacion();
        notificacion.setMensaje("Mensaje de prueba");
        notificacion.setEstado("PENDIENTE");
        notificacion.setTipo("SISTEMA");
        notificacion.setDestinatarioId(1L);
        notificacion.setFechaEnvio(LocalDateTime.now());

        when(notificacionService.getAllNotificaciones())
            .thenReturn(Arrays.asList(notificacion));

        mockMvc.perform(get("/notificaciones")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].mensaje").value("Mensaje de prueba"))
                .andExpect(jsonPath("$[0].estado").value("PENDIENTE"));
    }

    @Test
    void testCreateNotificacion() throws Exception {
        Notificacion notificacion = new Notificacion();
        notificacion.setMensaje("Nueva notificación");
        notificacion.setEstado("PENDIENTE");
        notificacion.setTipo("SISTEMA");
        notificacion.setDestinatarioId(1L);
        notificacion.setFechaEnvio(LocalDateTime.now());

        Notificacion notificacionGuardada = new Notificacion();
        BeanUtils.copyProperties(notificacion, notificacionGuardada);
        notificacionGuardada.setId(1L);

        when(notificacionService.createNotificacion(any(Notificacion.class)))
            .thenReturn(notificacionGuardada);

        mockMvc.perform(post("/notificaciones")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(notificacion)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.mensaje").value("Nueva notificación"));
    }

    @Test
    void testUpdateEstadoNotificacion() throws Exception {
        Notificacion notificacion = new Notificacion();
        notificacion.setId(1L);
        notificacion.setMensaje("Mensaje a actualizar");
        notificacion.setEstado("LEIDO");
        notificacion.setTipo("SISTEMA");
        notificacion.setDestinatarioId(1L);
        notificacion.setFechaEnvio(LocalDateTime.now());

        when(notificacionService.updateEstadoNotificacion(eq(1L), eq("LEIDO")))
                .thenReturn(Optional.of(notificacion));

        mockMvc.perform(put("/notificaciones/{id}/estado", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .param("nuevoEstado", "LEIDO"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.estado").value("LEIDO"));
    }
}