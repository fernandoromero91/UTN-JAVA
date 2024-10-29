package com.itr.reserva_baile.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstudioDeBaile {
    @Id
    private Long id;
    private String nombre;
    private String ubicacion;
    private int capacidad;
    private double precioHora;
    private boolean disponibilidad; // true si está disponible, false si está reservado
}
