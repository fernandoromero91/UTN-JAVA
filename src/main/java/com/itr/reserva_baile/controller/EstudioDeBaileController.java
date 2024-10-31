package com.itr.reserva_baile.controller;

import com.itr.reserva_baile.model.EstudioDeBaile;
import com.itr.reserva_baile.service.EstudioDeBaileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.stream.Collectors;


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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error de validación: " + errorMessage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstudioDeBaile> updateEstudio(@PathVariable Long id, @RequestBody EstudioDeBaile estudioDetails) {
        try {
            return ResponseEntity.ok(estudioDeBaileService.updateEstudio(id, estudioDetails));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstudio(@PathVariable Long id) {
        estudioDeBaileService.deleteEstudio(id);
        return ResponseEntity.noContent().build();
    }
}
