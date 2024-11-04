package com.itr.reserva_baile.service;

import com.itr.reserva_baile.dto.ReservaResponseDTO;
import com.itr.reserva_baile.model.ClaseDeBaile;
import com.itr.reserva_baile.model.EstudioDeBaile;
import com.itr.reserva_baile.model.Reserva;
import com.itr.reserva_baile.model.Usuario;
import com.itr.reserva_baile.repository.ReservaRepository;
import com.itr.reserva_baile.service.UsuarioService;
import com.itr.reserva_baile.service.ClaseDeBaileService;
import com.itr.reserva_baile.service.EstudioDeBaileService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final UsuarioService usuarioService;
    private final ClaseDeBaileService claseDeBaileService;
    private final EstudioDeBaileService estudioDeBaileService;

    @Autowired
    public ReservaService(ReservaRepository reservaRepository,
                         UsuarioService usuarioService,
                         ClaseDeBaileService claseDeBaileService,
                         EstudioDeBaileService estudioDeBaileService) {
        this.reservaRepository = reservaRepository;
        this.usuarioService = usuarioService;
        this.claseDeBaileService = claseDeBaileService;
        this.estudioDeBaileService = estudioDeBaileService;
    }

    public Iterable<Reserva> getAllReservas() {
        return reservaRepository.findAll();
    }

    public Reserva createReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    public Reserva updateReserva(Long id, Reserva reservaDetails) {
        return reservaRepository.findById(id)
                .map(reserva -> {
                    reserva.setFecha(reservaDetails.getFecha());
                    reserva.setHora(reservaDetails.getHora());
                    reserva.setDuracion(reservaDetails.getDuracion());
                    reserva.setEstado(reservaDetails.getEstado());
                    reserva.setUsuarioId(reservaDetails.getUsuarioId());
                    reserva.setClaseId(reservaDetails.getClaseId());
                    reserva.setEstudioId(reservaDetails.getEstudioId());
                    return reservaRepository.save(reserva);
                })
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
    }

    public Reserva getReservaById(Long id) {
        return reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada con id: " + id));
    }

    public void deleteReserva(Long id) {
        reservaRepository.deleteById(id);
    }

    public Iterable<ReservaResponseDTO> getAllReservasWithDetails() {
        List<ReservaResponseDTO> reservasDetalladas = new ArrayList<>();
        reservaRepository.findAll().forEach(reserva -> {
            try {
                Usuario usuario = usuarioService.getUsuarioById(reserva.getUsuarioId())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
                ClaseDeBaile clase = claseDeBaileService.getClaseById(reserva.getClaseId());
                EstudioDeBaile estudio = estudioDeBaileService.getEstudioById(reserva.getEstudioId());

                reservasDetalladas.add(new ReservaResponseDTO(
                    reserva.getId(),
                    reserva.getFecha(),
                    reserva.getHora(),
                    reserva.getDuracion(),
                    reserva.getEstado(),
                    usuario,
                    clase,
                    estudio
                ));
            } catch (RuntimeException e) {
                // Manejar la excepción según sea necesario
                System.err.println("Error al obtener detalles de la reserva: " + e.getMessage());
            }
        });
        return reservasDetalladas;
    }
}