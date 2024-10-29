package com.itr.reserva_baile.service;

import com.itr.reserva_baile.model.Usuario;
import com.itr.reserva_baile.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Iterable<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> getUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario createUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario updateUsuario(Long id, Usuario usuarioDetails) {
        return usuarioRepository.findById(id)
            .map(usuario -> {
                usuario.setNombre(usuarioDetails.getNombre());
                usuario.setEmail(usuarioDetails.getEmail());
                usuario.setTelefono(usuarioDetails.getTelefono());
                usuario.setRol(usuarioDetails.getRol());
                return usuarioRepository.save(usuario);
            })
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
