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
@Table("estudio_de_baile")
public class EstudioDeBaile {
    @Id
    private Long id;
    private String nombre;
    private String ubicacion;
    private int capacidad;
    @Column("precioHora")
    private double precioHora;
    private boolean disponibilidad; // true si está disponible, false si está reservado
}
