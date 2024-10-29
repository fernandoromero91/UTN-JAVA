package com.itr.reserva_baile.service;

import com.itr.reserva_baile.model.ClaseDeBaile;
import com.itr.reserva_baile.repository.ClaseDeBaileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClaseDeBaileService {

    private final ClaseDeBaileRepository claseDeBaileRepository;

    @Autowired
    public ClaseDeBaileService(ClaseDeBaileRepository claseDeBaileRepository) {
        this.claseDeBaileRepository = claseDeBaileRepository;
    }

    public Iterable<ClaseDeBaile> getAllClases() {
        return claseDeBaileRepository.findAll();
    }

    public ClaseDeBaile getClaseByNombre(String nombre) {
        return claseDeBaileRepository.findByNombre(nombre).iterator().next();
    }

    public ClaseDeBaile createClase(ClaseDeBaile claseDeBaile) {
        return claseDeBaileRepository.save(claseDeBaile);
    }

    public ClaseDeBaile updateClase(Long id, ClaseDeBaile claseDetails) {
        return claseDeBaileRepository.findById(id)
                .map(clase -> {
                    clase.setNombre(claseDetails.getNombre());
                    clase.setInstructor(claseDetails.getInstructor());
                    clase.setNivelDificultad(claseDetails.getNivelDificultad());
                    clase.setDuracion(claseDetails.getDuracion());
                    clase.setHorario(claseDetails.getHorario());
                    clase.setCapacidad(claseDetails.getCapacidad());
                    clase.setPrecio(claseDetails.getPrecio());
                    return claseDeBaileRepository.save(clase);
                })
                .orElseThrow(() -> new RuntimeException("Clase no encontrada"));
    }

    public void deleteClase(Long id) {
        claseDeBaileRepository.deleteById(id);
    }
}