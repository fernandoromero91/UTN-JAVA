package com.itr.reserva_baile.service;

import com.itr.reserva_baile.model.EstudioDeBaile;
import com.itr.reserva_baile.model.Reserva;
import com.itr.reserva_baile.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;

    @Autowired
    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
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
}