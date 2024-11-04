package com.itr.reserva_baile.controller;

import com.itr.reserva_baile.model.Membresia;
import com.itr.reserva_baile.service.MembresiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/membresias")
public class MembresiaController {

    private final MembresiaService membresiaService;

    @Autowired
    public MembresiaController(MembresiaService membresiaService) {
        this.membresiaService = membresiaService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Membresia>> getAllMembresia() {
        return ResponseEntity.ok(membresiaService.getAllMembresias());
    }

    @PostMapping
    public ResponseEntity<Membresia> createMembresia(@Valid @RequestBody Membresia membresia) {
        System.out.println("Intentando crear membresia con datos: " + membresia);
        return new ResponseEntity<>(membresiaService.createMembresia(membresia), HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Membresia> updateMembresia(@PathVariable Long id,@Valid @RequestBody Membresia membresiaDetails) {
        try {
            return ResponseEntity.ok(membresiaService.updateMembresia(id, membresiaDetails));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMembresia(@PathVariable Long id) {
        membresiaService.deleteMembresia(id);
        return ResponseEntity.noContent().build();
    }
	
    @GetMapping("/usuario/{IdUsuario}")
    public ResponseEntity<Iterable<Membresia>> getMembresiaByUsuario(@PathVariable Long IdUsuario) {
        return ResponseEntity.ok(membresiaService.getMembresiaByUsuario(IdUsuario));
    }

     @GetMapping("/{nombre}")
    public ResponseEntity<Membresia> getMembresiaByNombre(@PathVariable String NombreMembresia) {
        try {
            return ResponseEntity.ok((Membresia) membresiaService.getMembresiaByNombre(NombreMembresia));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
