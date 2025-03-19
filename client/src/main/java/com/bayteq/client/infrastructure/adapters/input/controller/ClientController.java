package com.bayteq.client.infrastructure.adapters.input.controller;

import com.bayteq.client.application.ports.input.IClientService;
import com.bayteq.client.domain.models.Client;
import com.bayteq.client.infrastructure.adapters.input.dto.ClientRequestDTO;
import com.bayteq.client.infrastructure.adapters.input.dto.ClientResponseDTO;
import com.bayteq.client.infrastructure.adapters.input.mapper.ClientDTOMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final IClientService clientService;
    private final ClientDTOMapper mapper;

    public ClientController(IClientService clientService, ClientDTOMapper mapper) {
        this.clientService = clientService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<ClientResponseDTO> createClient(@Valid @RequestBody ClientRequestDTO clientRequestDTO) {
        Client client = mapper.toClient(clientRequestDTO);
        Client createdClient = clientService.createClient(client);
        return new ResponseEntity<>(mapper.toResponseDTO(createdClient), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ClientResponseDTO>> getAllClients() {
        List<Client> clients = clientService.getAllClients();
        List<ClientResponseDTO> clientDTOs = clients.stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(clientDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> getClientById(@PathVariable Long id) {
        return clientService.getClientById(id)
                .map(client -> ResponseEntity.ok(mapper.toResponseDTO(client)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> updateClient(@PathVariable Long id,
                                                          @Valid @RequestBody ClientRequestDTO clientRequestDTO) {
        Client client = mapper.toClient(clientRequestDTO);
        Client updatedClient = clientService.updateClient(id, client);
        return ResponseEntity.ok(mapper.toResponseDTO(updatedClient));
    }

}
