package com.itr.reserva_baile.controller;

import com.itr.reserva_baile.model.Notificacion;
import com.itr.reserva_baile.service.NotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/notificaciones")
@CrossOrigin(origins = "*")
public class NotificacionController {

    private final NotificacionService notificacionService;

    @Autowired
    public NotificacionController(NotificacionService notificacionService) {
        this.notificacionService = notificacionService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Notificacion>> getAllNotificaciones() {
        return ResponseEntity.ok(notificacionService.getAllNotificaciones());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notificacion> getNotificacionById(@PathVariable Long id) {
        return notificacionService.getNotificacionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Notificacion> createNotificacion(@RequestBody Notificacion notificacion) {
        Notificacion nuevaNotificacion = notificacionService.createNotificacion(notificacion);
        return new ResponseEntity<>(nuevaNotificacion, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Notificacion> updateNotificacion(
            @PathVariable Long id,
            @RequestBody Notificacion notificacion) {
        try {
            Notificacion updatedNotificacion = notificacionService.updateNotificacion(id, notificacion);
            return ResponseEntity.ok(updatedNotificacion);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotificacion(@PathVariable Long id) {
        notificacionService.deleteNotificacion(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<Iterable<Notificacion>> getNotificacionesByEstado(@PathVariable String estado) {
        return ResponseEntity.ok(notificacionService.getNotificacionesByEstado(estado));
    }

    @GetMapping("/destinatario/{destinatarioId}")
    public ResponseEntity<Iterable<Notificacion>> getNotificacionesByDestinatario(@PathVariable Long destinatarioId) {
        return ResponseEntity.ok(notificacionService.getNotificacionesByDestinatario(destinatarioId));
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<Notificacion> updateEstadoNotificacion(
            @PathVariable Long id,
            @RequestParam String nuevoEstado) {
        return notificacionService.updateEstadoNotificacion(id, nuevoEstado)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}