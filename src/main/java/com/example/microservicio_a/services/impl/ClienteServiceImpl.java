package com.example.microservicio_a.services.impl;

import com.example.microservicio_a.dtos.ClienteResponse;
import com.example.microservicio_a.entities.ClienteEntity;
import com.example.microservicio_a.models.Cliente;
import com.example.microservicio_a.repositories.ClienteRepository;
import com.example.microservicio_a.request.ClienteRequest;
import com.example.microservicio_a.services.ClienteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository, ModelMapper modelMapper) {
        this.clienteRepository = clienteRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ClienteResponse registrarCliente(ClienteRequest clienteRequest) {
        // Generar un UUID aleatorio
        UUID secretUUID = UUID.randomUUID();

        // Crear un objeto ClienteEntity con los datos recibidos
        ClienteEntity clienteEntity = ClienteEntity.builder()
                .email(clienteRequest.getEmail())
                .tempFavorite(clienteRequest.getTempFavorite())
                .secret(secretUUID.toString())
                .build();
        // Guardar el clienteEntity en la base de datos
        ClienteEntity savedClienteEntity = clienteRepository.save(clienteEntity);

        // Crear un objeto ClienteResponse con el id y el secret generados
        ClienteResponse savedClienteResponse = ClienteResponse.builder()
                .id(savedClienteEntity.getId())
                .secret(savedClienteEntity.getSecret())
                .build();

        return savedClienteResponse;
    }
}

