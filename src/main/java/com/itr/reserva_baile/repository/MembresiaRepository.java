package com.itr.reserva_baile.repository;

import com.itr.reserva_baile.model.Membresia;
import org.springframework.data.repository.CrudRepository;

public interface MembresiaRepository extends CrudRepository<Membresia, Long> {
    // Métodos personalizados para buscar membresias
    Iterable<Membresia> findByIdUsuario(Long IdUsuario);
    Iterable<Membresia> findByNombreMembresia(String NombreMembresia);
}