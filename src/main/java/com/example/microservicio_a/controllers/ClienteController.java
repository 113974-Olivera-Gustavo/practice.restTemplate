package com.example.microservicio_a.controllers;

import com.example.microservicio_a.dtos.ClienteResponse;
import com.example.microservicio_a.models.Cliente;
import com.example.microservicio_a.request.ClienteRequest;
import com.example.microservicio_a.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;
    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }
    @PostMapping("/registrar")
    public ResponseEntity<ClienteResponse> registrarCliente(@RequestBody ClienteRequest clienteRequest) {
        ClienteResponse clienteResponse = clienteService.registrarCliente(clienteRequest);
        return ResponseEntity.ok(clienteResponse);
    }

}
