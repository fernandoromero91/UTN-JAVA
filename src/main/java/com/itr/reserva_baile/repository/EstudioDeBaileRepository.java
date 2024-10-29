package com.itr.reserva_baile.repository;

import com.itr.reserva_baile.model.EstudioDeBaile;
import org.springframework.data.repository.CrudRepository;

// repositorio para la entidad EstudioDeBaile, spring data generara la implementacion de la interfaz
public interface EstudioDeBaileRepository extends CrudRepository<EstudioDeBaile, Long> {
    // Método personalizado para buscar estudios disponibles
    Iterable<EstudioDeBaile> findByDisponibilidad(boolean disponibilidad);
}
