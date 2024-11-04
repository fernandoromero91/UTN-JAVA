package com.itr.reserva_baile.controller;

import com.itr.reserva_baile.model.EstudioDeBaile;
import com.itr.reserva_baile.service.EstudioDeBaileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Estudios de Baile", description = "API para gestionar estudios de baile")
@RestController
@RequestMapping("/estudios")
public class EstudioDeBaileController {

    private final EstudioDeBaileService estudioDeBaileService;

    @Autowired
    public EstudioDeBaileController(EstudioDeBaileService estudioDeBaileService) {
        this.estudioDeBaileService = estudioDeBaileService;
    }

    @GetMapping
    public ResponseEntity<Iterable<EstudioDeBaile>> getAllEstudios() {
        return ResponseEntity.ok(estudioDeBaileService.getAllEstudios());
    }

    @PostMapping
    public ResponseEntity<EstudioDeBaile> createEstudio(@Valid @RequestBody EstudioDeBaile estudioDeBaile) {
        return new ResponseEntity<>(estudioDeBaileService.createEstudio(estudioDeBaile), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstudioDeBaile> updateEstudio(@PathVariable Long id,
            @Valid @RequestBody EstudioDeBaile estudioDetails) {
        try {
            return ResponseEntity.ok(estudioDeBaileService.updateEstudio(id, estudioDetails));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstudioDeBaile> getEstudioById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(estudioDeBaileService.getEstudioById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstudio(@PathVariable Long id) {
        try {
            // Primero verificamos si existe
            estudioDeBaileService.getEstudioById(id);
            estudioDeBaileService.deleteEstudio(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
