package com.itr.reserva_baile.model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestConnectionController {

    @GetMapping("/test-connection")
    public String testConnection() {
        return "Conexión exitosa!";
    }
}
