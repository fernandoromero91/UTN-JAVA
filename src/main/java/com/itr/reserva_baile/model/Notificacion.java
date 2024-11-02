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

@Data // Genera automáticamente getters y setters, entre otros
@NoArgsConstructor // Genera un constructor sin argumentos
@AllArgsConstructor // Genera un constructor con un parámetro para cada campo
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
}