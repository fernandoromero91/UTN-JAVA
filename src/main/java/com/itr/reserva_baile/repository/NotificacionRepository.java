package com.itr.reserva_baile.repository;

import com.itr.reserva_baile.model.Notificacion;
import org.springframework.data.repository.CrudRepository;

public interface NotificacionRepository extends CrudRepository<Notificacion, Long> {
    // Métodos personalizados para buscar notificaciones
    Iterable<Notificacion> findByEstado(String estado);
    Iterable<Notificacion> findByTipo(String tipo);
    Iterable<Notificacion> findByDestinatarioId(Long destinatarioId);
    Iterable<Notificacion> findByEstadoAndDestinatarioId(String estado, Long destinatarioId);
}