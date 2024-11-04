package com.itr.reserva_baile.controller;

import com.itr.reserva_baile.model.Resenia;
import com.itr.reserva_baile.service.ReseniaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/resenias")
public class ReseniaController {

    private final ReseniaService reseniaService;

    @Autowired
    public ReseniaController(ReseniaService reseniaService) {
        this.reseniaService = reseniaService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Resenia>> getAllResenias() {
        return ResponseEntity.ok(reseniaService.getAllResenias());
    }

    @PostMapping
    public ResponseEntity<Resenia> createResenia(@Valid @RequestBody Resenia resenia) {
        System.out.println("Intentando crear resenia con datos: " + resenia);
        return new ResponseEntity<>(reseniaService.createResenia(resenia), HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Resenia> updateResenia(@PathVariable Long id,@Valid @RequestBody Resenia reseniaDetails) {
        try {
            return ResponseEntity.ok(reseniaService.updateResenia(id, reseniaDetails));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResenia(@PathVariable Long id) {
        reseniaService.deleteResenia(id);
        return ResponseEntity.noContent().build();
    }
	
    @GetMapping("/usuario/{IdUsuario}")
    public ResponseEntity<Iterable<Resenia>> getReseniaByUsuario(@PathVariable Long IdUsuario) {
        return ResponseEntity.ok(reseniaService.getReseniaByUsuario(IdUsuario));
    }

    @GetMapping("/clase/{IdClase}")
    public ResponseEntity<Iterable<Resenia>> getReseniaByClase(@PathVariable Long IdClase) {
        return ResponseEntity.ok(reseniaService.getReseniaByClase(IdClase));
    }

    @GetMapping("/estudio/{IdEstudio}")
    public ResponseEntity<Iterable<Resenia>> getReseniaByEstudio(@PathVariable Long IdEstudio) {
        return ResponseEntity.ok(reseniaService.getReseniaByEstudio(IdEstudio));
    }
	
}
