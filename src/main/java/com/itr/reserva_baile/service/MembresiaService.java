package com.itr.reserva_baile.service;

import com.itr.reserva_baile.model.Membresia;
import com.itr.reserva_baile.repository.MembresiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class MembresiaService {

    private final MembresiaRepository membresiaRepository;

    @Autowired
    public MembresiaService(MembresiaRepository membresiaRepository) {
        this.membresiaRepository = membresiaRepository;
    }

    public Iterable<Membresia> getAllMembresias() {
        return membresiaRepository.findAll();
    }

    public Optional<Membresia> getMembresiaById(Long id_membresia) {
        return membresiaRepository.findById(id_membresia);
    }

    public Membresia createMembresia(Membresia membresia) {
        return membresiaRepository.save(membresia);
    }
	
    public Membresia updateMembresia(Long id_membresia, Membresia membresiaDetails) {
        return membresiaRepository.findById(id_membresia)
            .map(membresia -> {
                membresia.setIdUsuario(membresiaDetails.getIdUsuario());
                membresia.setNombreMembresia(membresiaDetails.getNombreMembresia());
                membresia.setBeneficio(membresiaDetails.getBeneficio());
                membresia.setDescuento(membresiaDetails.getDescuento());
                membresia.setPrecio(membresiaDetails.getPrecio());
                membresia.setFechaInicio(membresiaDetails.getFechaInicio());
                membresia.setFechaFin(membresiaDetails.getFechaFin());
                return membresiaRepository.save(membresia);
            })
            .orElseThrow(() -> new RuntimeException("Membresia no encontrada"));
    }

    public void deleteMembresia(Long id_membresia) {
        membresiaRepository.deleteById(id_membresia);
    }

    // Métodos adicionales específicos
     public Iterable<Membresia> getMembresiaByUsuario(Long IdUsuario) {
        return membresiaRepository.findByIdUsuario(IdUsuario);
    }
    public Iterable<Membresia> getMembresiaByNombre(String Nombre) {
        return membresiaRepository.findByNombreMembresia(Nombre);
    }

}