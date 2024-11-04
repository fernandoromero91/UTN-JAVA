package com.itr.reserva_baile.integration.controller;

import com.itr.reserva_baile.model.ClaseDeBaile;
import com.itr.reserva_baile.model.EstudioDeBaile;
import com.itr.reserva_baile.model.Reserva;
import com.itr.reserva_baile.model.Usuario;
import com.itr.reserva_baile.repository.ClaseDeBaileRepository;
import com.itr.reserva_baile.repository.EstudioDeBaileRepository;
import com.itr.reserva_baile.repository.ReservaRepository;
import com.itr.reserva_baile.repository.UsuarioRepository;

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
public class ReservaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ReservaRepository reservaRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private ClaseDeBaileRepository claseDeBaileRepository;
    
    @Autowired
    private EstudioDeBaileRepository estudioDeBaileRepository;

    private Usuario usuario;
    private ClaseDeBaile clase;
    private EstudioDeBaile estudio;

    @BeforeEach
    void setUp() {
        reservaRepository.deleteAll();
        claseDeBaileRepository.deleteAll();
        estudioDeBaileRepository.deleteAll();
        usuarioRepository.deleteAll();

        // Crear registros necesarios
        usuario = usuarioRepository.save(new Usuario(null, "Usuario Test", "test@test.com", "12345678", "USER"));
        clase = claseDeBaileRepository.save(new ClaseDeBaile(null, "Salsa", "Instructor", "Básico", 60, "10:00", 20, 25.0));
        estudio = estudioDeBaileRepository.save(new EstudioDeBaile(null, "Estudio A", "Dirección", 30, 20.0, true));
    }

    @Test
    void testGetAllReservas() throws Exception {
        Reserva reserva = new Reserva(null, usuario.getId(), clase.getId(), estudio.getId(), "2024-03-20", "10:00", 60, "confirmada");
        reservaRepository.save(reserva);

        mockMvc.perform(get("/reservas")
                .with(httpBasic("admin", "admin"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].fecha", is("2024-03-20")))
                .andExpect(jsonPath("$[0].estado", is("confirmada")));
    }

    @Test
    void testCreateReserva() throws Exception {
        String reservaJson = "{ \"usuarioId\": " + usuario.getId() + ", \"claseId\": " + clase.getId() + ", \"estudioId\": " + estudio.getId() + ", \"fecha\": \"2024-03-20\", \"hora\": \"10:00\", \"duracion\": 60, \"estado\": \"confirmada\" }";
        mockMvc.perform(post("/reservas")
                .with(httpBasic("admin", "admin"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(reservaJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.fecha", is("2024-03-20")))
                .andExpect(jsonPath("$.estado", is("confirmada")));
    }

    @Test
    void testGetReservaById() throws Exception {
        // Crear entidades necesarias primero
        Usuario usuario = usuarioRepository.save(new Usuario(null, "Usuario Test", "test@test.com", "12345678", "USER"));
        ClaseDeBaile clase = claseDeBaileRepository.save(new ClaseDeBaile(null, "Salsa", "Instructor", "Básico", 60, "10:00", 20, 25.0));
        EstudioDeBaile estudio = estudioDeBaileRepository.save(new EstudioDeBaile(null, "Estudio A", "Dirección", 30, 20.0, true));

        // Crear la reserva usando los IDs generados
        Reserva reserva = new Reserva(null, usuario.getId(), clase.getId(), estudio.getId(), "2024-03-20", "10:00", 60, "confirmada");
        Reserva reservaGuardada = reservaRepository.save(reserva);

        mockMvc.perform(get("/reservas/{id}", reservaGuardada.getId())
                .with(httpBasic("admin", "admin")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fecha", is("2024-03-20")))
                .andExpect(jsonPath("$.estado", is("confirmada")));
    }

    @Test
    void testUpdateReserva() throws Exception {
        // Crear la reserva inicial usando las variables de instancia
        Reserva reserva = new Reserva(null, usuario.getId(), clase.getId(), estudio.getId(), "2024-03-20", "10:00", 60, "confirmada");
        Reserva reservaGuardada = reservaRepository.save(reserva);

        // Crear el JSON para actualizar
        String reservaActualizadaJson = String.format(
            "{ \"usuarioId\": %d, \"claseId\": %d, \"estudioId\": %d, \"fecha\": \"2024-03-21\", \"hora\": \"11:00\", \"duracion\": 90, \"estado\": \"pendiente\" }",
            usuario.getId(), clase.getId(), estudio.getId()
        );

        // Realizar la actualización
        mockMvc.perform(put("/reservas/{id}", reservaGuardada.getId())
                .with(httpBasic("admin", "admin"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(reservaActualizadaJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fecha", is("2024-03-21")))
                .andExpect(jsonPath("$.estado", is("pendiente")));
    }

    @Test
    void testDeleteReserva() throws Exception {
        // Crear entidades necesarias primero
        Usuario usuario = usuarioRepository.save(new Usuario(null, "Usuario Test", "test@test.com", "12345678", "USER"));
        ClaseDeBaile clase = claseDeBaileRepository.save(new ClaseDeBaile(null, "Salsa", "Instructor", "Básico", 60, "10:00", 20, 25.0));
        EstudioDeBaile estudio = estudioDeBaileRepository.save(new EstudioDeBaile(null, "Estudio A", "Dirección", 30, 20.0, true));
    
        // Crear la reserva usando los IDs generados
        Reserva reserva = new Reserva(null, usuario.getId(), clase.getId(), estudio.getId(), "2024-03-20", "10:00", 60, "confirmada");
        Reserva reservaGuardada = reservaRepository.save(reserva);
    
        // Resto del test...
        mockMvc.perform(delete("/reservas/{id}", reservaGuardada.getId())
                .with(httpBasic("admin", "admin")))
                .andExpect(status().isNoContent());
    }

    @Test
    void testCreateReservaWithInvalidData() throws Exception {
        String reservaInvalidaJson = "{ \"usuarioId\": null, \"claseId\": null, \"estudioId\": null, \"fecha\": \"\", \"hora\": \"\", \"duracion\": -1, \"estado\": \"INVALID_STATUS\" }";

        mockMvc.perform(post("/reservas")
                .with(httpBasic("admin", "admin"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(reservaInvalidaJson))
                .andExpect(status().isBadRequest());
    }
}