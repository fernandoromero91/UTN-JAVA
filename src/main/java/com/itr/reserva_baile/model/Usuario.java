package com.itr.reserva_baile.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Modelo de Usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("usuario")
public class Usuario {
    @Id
    private Long id;

    @NotEmpty(message = "El nombre es obligatorio")
    private String nombre;

    @NotEmpty(message = "El email es obligatorio")
    @Email(message = "El formato del email no es válido")
    private String email;

    @NotEmpty(message = "El teléfono es obligatorio")
    @Pattern(regexp = "^[0-9]{8,}$", message = "El teléfono debe tener al menos 8 dígitos")
    private String telefono;

    @NotEmpty(message = "El rol es obligatorio")
    @Pattern(regexp = "^(USER|ADMIN)$", message = "El rol debe ser 'USER' o 'ADMIN'")
    private String rol;
}
