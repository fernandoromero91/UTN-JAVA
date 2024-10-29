package com.itr.reserva_baile.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("reserva")
public class Reserva {
    @Id
    private Long id;

    @Column("usuario_id")
    private Long usuarioId;

    @Column("clase_id")
    private Long claseId;

    @Column("estudio_id")
    private Long estudioId;

    private String fecha; // fecha de la reserva
    private String hora;  // hora de la reserva
    private int duracion; // duración de la reserva en minutos
    private String estado; // Ej. "confirmada", "cancelada", "modificada"
}
