package com.itr.reserva_baile.service;

import com.itr.reserva_baile.model.EstudioDeBaile;
import com.itr.reserva_baile.repository.EstudioDeBaileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstudioDeBaileService {

    private final EstudioDeBaileRepository estudioDeBaileRepository;

    @Autowired
    public EstudioDeBaileService(EstudioDeBaileRepository estudioDeBaileRepository) {
        this.estudioDeBaileRepository = estudioDeBaileRepository;
    }

    public Iterable<EstudioDeBaile> getAllEstudios() {
        return estudioDeBaileRepository.findAll();
    }

    public EstudioDeBaile createEstudio(EstudioDeBaile estudioDeBaile) {
        return estudioDeBaileRepository.save(estudioDeBaile);
    }

    public EstudioDeBaile updateEstudio(Long id, EstudioDeBaile estudioDetails) {
        return estudioDeBaileRepository.findById(id)
                .map(estudio -> {
                    estudio.setNombre(estudioDetails.getNombre());
                    estudio.setUbicacion(estudioDetails.getUbicacion());
                    estudio.setCapacidad(estudioDetails.getCapacidad());
                    estudio.setPrecioHora(estudioDetails.getPrecioHora());
                    estudio.setDisponibilidad(estudioDetails.getDisponibilidad());
                    return estudioDeBaileRepository.save(estudio);
                })
                .orElseThrow(() -> new RuntimeException("Estudio no encontrado"));
    }

    public void deleteEstudio(Long id) {
        estudioDeBaileRepository.deleteById(id);
    }

    public EstudioDeBaile getEstudioById(Long id) {
        return estudioDeBaileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudio de baile no encontrado con id: " + id));
    }
}