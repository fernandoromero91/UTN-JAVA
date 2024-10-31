package com.itr.reserva_baile.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("reserva")
public class Reserva {
    @Id
    private Long id;

    @NotNull(message = "El usuarioId es obligatorio")
    @Column("usuario_id")
    private Long usuarioId;

    @NotNull(message = "La claseId es obligatoria")
    @Column("clase_id")
    private Long claseId;

    @NotNull(message = "El estudioId es obligatorio")
    @Column("estudio_id")
    private Long estudioId;

    @NotEmpty(message = "La fecha es obligatoria")
    private String fecha; // fecha de la reserva

    @NotEmpty(message = "La hora es obligatoria")
    private String hora;  // hora de la reserva

    @NotNull(message = "La duración es obligatoria")
    private Integer duracion; // duración de la reserva en minutos

    @NotEmpty(message = "El estado es obligatorio")
    private String estado; // Ej. "confirmada", "cancelada", "modificada"
}
