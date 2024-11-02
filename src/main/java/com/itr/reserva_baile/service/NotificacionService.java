package com.itr.reserva_baile.service;

import com.itr.reserva_baile.model.Notificacion;
import com.itr.reserva_baile.repository.NotificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NotificacionService {

    private final NotificacionRepository notificacionRepository;

    @Autowired
    public NotificacionService(NotificacionRepository notificacionRepository) {
        this.notificacionRepository = notificacionRepository;
    }

    public Iterable<Notificacion> getAllNotificaciones() {
        return notificacionRepository.findAll();
    }

    public Optional<Notificacion> getNotificacionById(Long id) {
        return notificacionRepository.findById(id);
    }

    public Notificacion createNotificacion(Notificacion notificacion) {
        return notificacionRepository.save(notificacion);
    }

    public Notificacion updateNotificacion(Long id, Notificacion notificacionDetails) {
        return notificacionRepository.findById(id)
            .map(notificacion -> {
                notificacion.setTipo(notificacionDetails.getTipo());
                notificacion.setMensaje(notificacionDetails.getMensaje());
                notificacion.setDestinatarioId(notificacionDetails.getDestinatarioId());
                notificacion.setFechaEnvio(notificacionDetails.getFechaEnvio());
                notificacion.setEstado(notificacionDetails.getEstado());
                return notificacionRepository.save(notificacion);
            })
            .orElseThrow(() -> new RuntimeException("Notificación no encontrada"));
    }

    public void deleteNotificacion(Long id) {
        notificacionRepository.deleteById(id);
    }

    // Métodos adicionales específicos
    public Iterable<Notificacion> getNotificacionesByEstado(String estado) {
        return notificacionRepository.findByEstado(estado);
    }

    public Iterable<Notificacion> getNotificacionesByDestinatario(Long destinatarioId) {
        return notificacionRepository.findByDestinatarioId(destinatarioId);
    }
}