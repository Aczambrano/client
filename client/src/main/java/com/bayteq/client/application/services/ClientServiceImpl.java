package com.bayteq.client.application.services;

import com.bayteq.client.application.ports.input.IClientService;
import com.bayteq.client.application.ports.output.IClientRepository;
import com.bayteq.client.domain.exception.ClientDocumentAlreadyExistsException;
import com.bayteq.client.domain.exception.ClientEmailAlreadyExistsException;
import com.bayteq.client.domain.exception.ClientNotFoundException;
import com.bayteq.client.domain.models.Client;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements IClientService {

    private final IClientRepository clientRepository;

    public ClientServiceImpl(IClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client createClient(Client client) {
        Optional.of(client.getDocumentNumber())
                .filter(clientRepository::existsByDocumentNumber)
                .ifPresent(doc -> {
                    throw new ClientDocumentAlreadyExistsException(doc);
                });

        Optional.of(client.getEmail())
                .filter(clientRepository::existsByEmail)
                .ifPresent(email -> {
                    throw new ClientEmailAlreadyExistsException(email);
                });

        return clientRepository.save(client);
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }

    @Override
    public Client updateClient(Long id, Client client) {
        clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));

        clientRepository.findByDocumentNumber(client.getDocumentNumber())
                .filter(existingClient -> !existingClient.getId().equals(id))
                .ifPresent(existing -> {
                    throw new ClientDocumentAlreadyExistsException(client.getDocumentNumber());
                });

        clientRepository.findByEmail(client.getEmail())
                .filter(existingClient -> !existingClient.getId().equals(id))
                .ifPresent(existing -> {
                    throw new ClientEmailAlreadyExistsException(client.getEmail());
                });

        client.setId(id);
        return clientRepository.save(client);
    }
}