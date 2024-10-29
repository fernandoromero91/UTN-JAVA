package com.itr.reserva_baile.repository;

import com.itr.reserva_baile.model.Reserva;
import org.springframework.data.repository.CrudRepository;

// repositorio para la entidad Reserva, spring data generara la implementacion de la interfaz
public interface ReservaRepository extends CrudRepository<Reserva, Long> {
    // Método personalizado para buscar reservas por usuario
    Iterable<Reserva> findByUsuarioId(Long usuarioId);
}
