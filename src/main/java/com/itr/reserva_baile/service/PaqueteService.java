package com.itr.reserva_baile.service;

import com.itr.reserva_baile.model.Paquete;
import com.itr.reserva_baile.repository.PaqueteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaqueteService {

    private final PaqueteRepository paqueteRepository;

    @Autowired
    public PaqueteService(PaqueteRepository paqueteRepository) {
        this.paqueteRepository = paqueteRepository;
    }

    public Iterable<Paquete> getAllPaquetes() {
        return paqueteRepository.findAll();
    }

    public Optional<Paquete> getPaqueteById(Long id) {
        return paqueteRepository.findById(id);
    }

    public Paquete createPaquete(Paquete paquete) {
        return paqueteRepository.save(paquete);
    }

    public Paquete updatePaquete(Long id, Paquete paqueteDetails) {
        return paqueteRepository.findById(id)
            .map(paquete -> {
                paquete.setNombrePaquete(paqueteDetails.getNombrePaquete());
                paquete.setDescripcion(paqueteDetails.getDescripcion());
                paquete.setPrecio(paqueteDetails.getPrecio());
                paquete.setDuracion(paqueteDetails.getDuracion());
                paquete.setClasesIncluidas(paqueteDetails.getClasesIncluidas());
                return paqueteRepository.save(paquete);
            })
            .orElseThrow(() -> new RuntimeException("Paquete no encontrado"));
    }

    public void deletePaquete(Long id) {
        paqueteRepository.deleteById(id);
    }

    // Métodos adicionales específicos
    public Iterable<Paquete> getPaquetesByPrecioMaximo(Double precioMaximo) {
        return paqueteRepository.findByPrecioLessThanEqual(precioMaximo);
    }

    public Iterable<Paquete> getPaquetesByNombreContaining(String nombre) {
        return paqueteRepository.findByNombrePaqueteContaining(nombre);
    }
    
    public Iterable<Paquete> getPaquetesByDuracion(Integer duracion) {
        return paqueteRepository.findByDuracion(duracion);
    }
}