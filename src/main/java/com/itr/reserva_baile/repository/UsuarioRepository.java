package com.itr.reserva_baile.repository;

import com.itr.reserva_baile.model.Usuario;
import org.springframework.data.repository.CrudRepository;


// repositorio para la entidad Usuario, spring data generara la implementacion de la interfaz
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
}
