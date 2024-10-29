package com.itr.reserva_baile.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("usuario")
public class Usuario {
    @Id
    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    private String rol; // puede ser 'USER' o 'ADMIN'
}
