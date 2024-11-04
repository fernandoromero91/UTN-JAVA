package com.itr.reserva_baile.unit.repository;

import com.itr.reserva_baile.model.Pago;
import com.itr.reserva_baile.repository.PagoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class PagoRepositoryTest {

    @Autowired
    private PagoRepository pagoRepository;

    private Pago pago;

    @BeforeEach
    void setUp() {
        pago = new Pago(null, 1L, null, 100.0, "2023-01-01", "Tarjeta de crédito");
    }

    @Test
    void testSavePago() {
        Pago savedPago = pagoRepository.save(pago);
        assertNotNull(savedPago.getId());
    }

    @Test
    void testFindById() {
        Pago savedPago = pagoRepository.save(pago);
        Optional<Pago> foundPago = pagoRepository.findById(savedPago.getId());
        assertTrue(foundPago.isPresent());
        assertEquals(savedPago.getMonto(), foundPago.get().getMonto());
    }
}
