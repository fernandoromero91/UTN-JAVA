package com.itr.reserva_baile.repository;

import com.itr.reserva_baile.model.ClaseDeBaile;
import org.springframework.data.repository.CrudRepository;

// repositorio para la entidad ClaseDeBaile, spring data generara la implementacion de la interfaz
public interface ClaseDeBaileRepository extends CrudRepository<ClaseDeBaile, Long> {
    //metodo para buscar una clase de baile por su nombre
    //Iterable es una interfaz que permite iterar sobre una coleccion de elementos
    Iterable<ClaseDeBaile> findByNombre(String nombre);
}
