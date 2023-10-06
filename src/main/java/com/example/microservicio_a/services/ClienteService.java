package com.example.microservicio_a.services;

import com.example.microservicio_a.dtos.ClienteResponse;
import com.example.microservicio_a.models.Cliente;
import com.example.microservicio_a.request.ClienteRequest;
import org.springframework.stereotype.Service;

@Service
public interface ClienteService {

    ClienteResponse registrarCliente(ClienteRequest cliente);
}
