package com.itr.reserva_baile.controller;

import com.itr.reserva_baile.model.ClaseDeBaile;
import com.itr.reserva_baile.service.ClaseDeBaileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clases")
public class ClaseDeBaileController {

    private final ClaseDeBaileService claseDeBaileService;

    @Autowired
    public ClaseDeBaileController(ClaseDeBaileService claseDeBaileService) {
        this.claseDeBaileService = claseDeBaileService;
    }

    @GetMapping
    public ResponseEntity<Iterable<ClaseDeBaile>> getAllClases() {
        return ResponseEntity.ok(claseDeBaileService.getAllClases());
    }

    @GetMapping("/{nombre}")
    public ResponseEntity<ClaseDeBaile> getClaseByNombre(@PathVariable String nombre) {
        try {
            return ResponseEntity.ok(claseDeBaileService.getClaseByNombre(nombre));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<ClaseDeBaile> createClase(@Valid @RequestBody ClaseDeBaile claseDeBaile) {
        return new ResponseEntity<>(claseDeBaileService.createClase(claseDeBaile), HttpStatus.CREATED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error de validación: " + errorMessage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClaseDeBaile> updateClase(@PathVariable Long id, @RequestBody ClaseDeBaile claseDetails) {
        try {
            return ResponseEntity.ok(claseDeBaileService.updateClase(id, claseDetails));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClase(@PathVariable Long id) {
        claseDeBaileService.deleteClase(id);
        return ResponseEntity.noContent().build();
    }
}
