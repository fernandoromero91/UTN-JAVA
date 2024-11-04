package com.itr.reserva_baile.repository;

import com.itr.reserva_baile.model.Resenia;
import org.springframework.data.repository.CrudRepository;

public interface ReseniaRepository extends CrudRepository<Resenia, Long> {
    // Métodos personalizados para buscar Reseñas
    Iterable<Resenia> findByIdUsuario(Long IdUsuario);
    Iterable<Resenia> findByIdClase(Long IdClase);
    Iterable<Resenia> findByIdEstudio(Long IdEstudio);
}