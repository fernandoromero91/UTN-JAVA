package com.itr.reserva_baile.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("notificacion")
public class Notificacion {
    @Id
    private Long id;

    @NotEmpty(message = "El tipo de notificación es obligatorio")
    private String tipo;

    @NotEmpty(message = "El mensaje es obligatorio")
    private String mensaje;

    @NotNull(message = "El ID del destinatario es obligatorio")
    private Long destinatarioId;

    @NotNull(message = "La fecha de envío es obligatoria")
    private LocalDateTime fechaEnvio;

    @NotEmpty(message = "El estado es obligatorio")
    private String estado;

    // Constructor adicional para tests
    public Notificacion(Long id, String mensaje, String estado, Long destinatarioId) {
        this.id = id;
        this.mensaje = mensaje;
        this.estado = estado;
        this.destinatarioId = destinatarioId;
        this.fechaEnvio = LocalDateTime.now();
        this.tipo = "SISTEMA";
    }
}