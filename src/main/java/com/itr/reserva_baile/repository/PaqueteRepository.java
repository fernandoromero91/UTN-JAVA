package com.itr.reserva_baile.repository;

import com.itr.reserva_baile.model.Paquete;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;

public interface PaqueteRepository extends CrudRepository<Paquete, Long> {
    // Métodos personalizados para buscar paquetes
    Iterable<Paquete> findByDuracion(Integer duracion);

    Iterable<Paquete> findByPrecioLessThanEqual(Double precio);
    Iterable<Paquete> findByNombrePaqueteContaining(String nombre);

    Iterable<Paquete> findByPrecioBetween(BigDecimal minPrecio, BigDecimal maxPrecio);
}
