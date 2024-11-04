package com.itr.reserva_baile.service;

import com.itr.reserva_baile.model.Pago;
import com.itr.reserva_baile.repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PagoService {

    private final PagoRepository pagoRepository;

    @Autowired
    public PagoService(PagoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }

    public Iterable<Pago> getAllPagos() {
        return pagoRepository.findAll();
    }

    public Optional<Pago> getPagoById(Long id) {
        return pagoRepository.findById(id);
    }

    public Pago createPago(Pago pago) {
        return pagoRepository.save(pago);
    }

    public Pago updatePago(Long id, Pago pagoDetails) {
        return pagoRepository.findById(id)
                .map(pago -> {
                    pago.setUsuarioId(pagoDetails.getUsuarioId());
                    pago.setReservaId(pagoDetails.getReservaId());
                    pago.setMonto(pagoDetails.getMonto());
                    pago.setFecha(pagoDetails.getFecha());
                    pago.setMetodoPago(pagoDetails.getMetodoPago());
                    return pagoRepository.save(pago);
                }).orElseThrow(() -> new RuntimeException("Pago no encontrado"));
    }

    public void deletePago(Long id) {
        pagoRepository.deleteById(id);
    }
}
