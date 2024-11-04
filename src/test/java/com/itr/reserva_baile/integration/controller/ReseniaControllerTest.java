package com.itr.reserva_baile.integration.controller;
 
import com.itr.reserva_baile.model.ClaseDeBaile;
import com.itr.reserva_baile.model.EstudioDeBaile;
import com.itr.reserva_baile.model.Resenia;
import com.itr.reserva_baile.model.Reserva;
import com.itr.reserva_baile.model.Usuario;
import com.itr.reserva_baile.repository.ReseniaRepository;
import com.itr.reserva_baile.repository.UsuarioRepository;
import com.itr.reserva_baile.repository.ClaseDeBaileRepository;
import com.itr.reserva_baile.repository.EstudioDeBaileRepository;
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
public class ReseniaControllerTest {
 
    @Autowired
    private MockMvc mockMvc;
 
    @Autowired
    private ReseniaRepository reseniaRepository;
 
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
        reseniaRepository.deleteAll();
        claseDeBaileRepository.deleteAll();
        estudioDeBaileRepository.deleteAll();
        usuarioRepository.deleteAll();
 
        // Crear registros necesarios
        usuario = usuarioRepository.save(new Usuario(null, "Usuario Test", "test@test.com", "12345678", "USER"));
        clase = claseDeBaileRepository.save(new ClaseDeBaile(null, "Salsa", "Instructor", "Básico", 60, "10:00", 20, 25.0));
        estudio = estudioDeBaileRepository.save(new EstudioDeBaile(null, "Estudio A", "Dirección", 30, 20.0, true));
    }
 
    @Test
    void testGetAllResenias() throws Exception {
        Resenia resenia = new Resenia(null, usuario.getId(), clase.getId(), estudio.getId(), "5", 1L, "2024-03-20");
        reseniaRepository.save(resenia);
 
        mockMvc.perform(get("/resenias")
                .with(httpBasic("admin", "admin"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].puntuacion", is("5")))
                .andExpect(jsonPath("$[0].fecha", is("2024-03-20")));
    }
 
 
    @Test
    void testCreateResenia() throws Exception {
        String reseniaJson = String.format(
            "{ \"idUsuario\": %d, \"idClase\": %d, \"idEstudio\": %d, \"puntuacion\": \"5\", \"comentario\": 1, \"fecha\": \"2024-03-20\" }",
            usuario.getId(), clase.getId(), estudio.getId()
        );
   
        mockMvc.perform(post("/resenias")
                .with(httpBasic("admin", "admin"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(reseniaJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.puntuacion", is("5")))
                .andExpect(jsonPath("$.fecha", is("2024-03-20")));
    }
 
    @Test
    void testUpdateResenia() throws Exception {
        Resenia resenia = new Resenia(null, usuario.getId(), clase.getId(), estudio.getId(), "5", 1L, "2024-03-20");
        Resenia reseniaGuardada = reseniaRepository.save(resenia);
   
        String reseniaActualizadaJson = String.format(
            "{ \"idUsuario\": %d, \"idClase\": %d, \"idEstudio\": %d, \"puntuacion\": \"4\", \"comentario\": 1, \"fecha\": \"2024-03-21\" }",
            usuario.getId(), clase.getId(), estudio.getId()
        );
   
        mockMvc.perform(put("/resenias/{id}", reseniaGuardada.getIdResenia())
                .with(httpBasic("admin", "admin"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(reseniaActualizadaJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.puntuacion", is("4")))
                .andExpect(jsonPath("$.fecha", is("2024-03-21")));
    }
 
    @Test
    void testDeleteResenia() throws Exception {
        Resenia resenia = new Resenia(null, usuario.getId(), clase.getId(), estudio.getId(), "5", 1L, "2024-03-20");
        Resenia reseniaGuardada = reseniaRepository.save(resenia);
   
        mockMvc.perform(delete("/resenias/{id}", reseniaGuardada.getIdResenia())
                .with(httpBasic("admin", "admin")))
                .andExpect(status().isNoContent());
    }
 
    @Test
    void testGetReseniasByUsuario() throws Exception {
        Resenia resenia = new Resenia(null, usuario.getId(), clase.getId(), estudio.getId(), "5", 1L, "2024-03-20");
        reseniaRepository.save(resenia);
   
        mockMvc.perform(get("/resenias/usuario/{IdUsuario}", usuario.getId())
                .with(httpBasic("admin", "admin")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idUsuario", is(usuario.getId().intValue())))
                .andExpect(jsonPath("$[0].puntuacion", is("5")));
    }
 
    @Test
    void testGetReseniasByClase() throws Exception {
        Resenia resenia = new Resenia(null, usuario.getId(), clase.getId(), estudio.getId(), "5", 1L, "2024-03-20");
        reseniaRepository.save(resenia);
   
        mockMvc.perform(get("/resenias/clase/{IdClase}", clase.getId())
                .with(httpBasic("admin", "admin")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idClase", is(clase.getId().intValue())))
                .andExpect(jsonPath("$[0].puntuacion", is("5")));
    }
 
    @Test
    void testGetReseniasByEstudio() throws Exception {
        Resenia resenia = new Resenia(null, usuario.getId(), clase.getId(), estudio.getId(), "5", 1L, "2024-03-20");
        reseniaRepository.save(resenia);
   
        mockMvc.perform(get("/resenias/estudio/{IdEstudio}", estudio.getId())
                .with(httpBasic("admin", "admin")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idEstudio", is(estudio.getId().intValue())))
                .andExpect(jsonPath("$[0].puntuacion", is("5")));
    }
 
    @Test
    void testCreateReseniaWithInvalidData() throws Exception {
        String reseniaInvalidaJson = "{ \"idUsuario\": null, \"idClase\": null, \"idEstudio\": null, \"puntuacion\": \"\", \"idReserva\": null, \"fecha\": \"\" }";
 
        mockMvc.perform(post("/resenias")
                .with(httpBasic("admin", "admin"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(reseniaInvalidaJson))
                .andExpect(status().isBadRequest());
    }
}