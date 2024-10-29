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
@Table("clase_de_baile")
public class ClaseDeBaile {
    @Id
    private Long id;
    private String nombre;
    private String instructor; // nombre del instructor, en algun futuro lo hare referencia a un usuario
    @Column("nivelDificultad")
    private String nivelDificultad;
    private int duracion; // en minutos
    private String horario; // Ej. "Lunes 18:00"
    private int capacidad; // número máximo de personas
    private double precio;
}
