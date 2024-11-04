package com.itr.reserva_baile.controller;

import com.itr.reserva_baile.model.Paquete;
import com.itr.reserva_baile.service.PaqueteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.math.BigDecimal;

@RestController
@RequestMapping("/paquetes")
@CrossOrigin(origins = "*")
public class PaqueteController {

    private final PaqueteService paqueteService;

    @Autowired
    public PaqueteController(PaqueteService paqueteService) {
        this.paqueteService = paqueteService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Paquete>> getAllPaquetes() {
        return ResponseEntity.ok(paqueteService.getAllPaquetes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paquete> getPaqueteById(@PathVariable Long id) {
        return paqueteService.getPaqueteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Paquete> createPaquete(@Valid @RequestBody Paquete paquete) {
        Paquete nuevoPaquete = paqueteService.createPaquete(paquete);
        return new ResponseEntity<>(nuevoPaquete, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paquete> updatePaquete(
            @PathVariable Long id,
            @Valid @RequestBody Paquete paquete) {
        try {
            Paquete updatedPaquete = paqueteService.updatePaquete(id, paquete);
            return ResponseEntity.ok(updatedPaquete);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaquete(@PathVariable Long id) {
        paqueteService.deletePaquete(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint adicional para buscar por rango de precio
    @GetMapping("/precio")
    public ResponseEntity<Iterable<Paquete>> getPaquetesByPrecioRange(
            @RequestParam BigDecimal minPrecio,
            @RequestParam BigDecimal maxPrecio) {
        return ResponseEntity.ok(paqueteService.getPaquetesByPrecioRange(minPrecio, maxPrecio));
    }

    // Endpoint adicional para buscar por duración
    @GetMapping("/duracion/{duracion}")
    public ResponseEntity<Iterable<Paquete>> getPaquetesByDuracion(@PathVariable Integer duracion) {
        return ResponseEntity.ok(paqueteService.getPaquetesByDuracion(duracion));
    }
}