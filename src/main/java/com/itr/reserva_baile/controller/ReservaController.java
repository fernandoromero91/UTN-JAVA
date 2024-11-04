package com.itr.reserva_baile.controller;

import com.itr.reserva_baile.model.Reserva;
import com.itr.reserva_baile.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Reservas", description = "API para gestionar reservas")
@RestController
@RequestMapping("/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    @Autowired
    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Reserva>> getAllReservas() {
        return ResponseEntity.ok(reservaService.getAllReservas());
    }

    @PostMapping
    public ResponseEntity<Reserva> createReserva(@Valid @RequestBody Reserva reserva) {
        System.out.println("Intentando crear reserva con datos: " + reserva);
        return new ResponseEntity<>(reservaService.createReserva(reserva), HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Reserva> updateReserva(@PathVariable Long id,@Valid @RequestBody Reserva reservaDetails) {
        try {
            return ResponseEntity.ok(reservaService.updateReserva(id, reservaDetails));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReserva(@PathVariable Long id) {
        reservaService.deleteReserva(id);
        return ResponseEntity.noContent().build();
    }
}
