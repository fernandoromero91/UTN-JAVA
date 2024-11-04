package com.itr.reserva_baile.service;

import com.itr.reserva_baile.model.Resenia;
import com.itr.reserva_baile.repository.ReseniaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ReseniaService {

    private final ReseniaRepository reseniaRepository;

    @Autowired
    public ReseniaService(ReseniaRepository reseniaRepository) {
        this.reseniaRepository = reseniaRepository;
    }

    public Iterable<Resenia> getAllResenias() {
        return reseniaRepository.findAll();
    }

    public Optional<Resenia> getReseniaById(Long id_resenia) {
        return reseniaRepository.findById(id_resenia);
    }

    public Resenia createResenia(Resenia resenia) {
        return reseniaRepository.save(resenia);
    }

    public Resenia updateResenia(Long id_resenia, Resenia reseniaDetails) {
        return reseniaRepository.findById(id_resenia)
            .map(resenia -> {
                resenia.setIdUsuario(reseniaDetails.getIdUsuario());
                resenia.setIdClase(reseniaDetails.getIdClase());
                resenia.setIdEstudio(reseniaDetails.getIdEstudio());
                resenia.setPuntuacion(reseniaDetails.getPuntuacion());
                resenia.setComentario(reseniaDetails.getComentario());
                resenia.setFecha(reseniaDetails.getFecha());
                return reseniaRepository.save(resenia);
            })
            .orElseThrow(() -> new RuntimeException("Resenia no encontrada"));
    }

    public void deleteResenia(Long id_resenia) {
        reseniaRepository.deleteById(id_resenia);
    }

    // Métodos adicionales específicos
     public Iterable<Resenia> getReseniaByUsuario(Long IdUsuario) {
        return reseniaRepository.findByIdUsuario(IdUsuario);
    }

     public Iterable<Resenia> getReseniaByClase(Long IdClase) {
        return reseniaRepository.findByIdClase(IdClase);
    }

     public Iterable<Resenia> getReseniaByEstudio(Long IdEstudio) {
        return reseniaRepository.findByIdEstudio(IdEstudio);
    }
}