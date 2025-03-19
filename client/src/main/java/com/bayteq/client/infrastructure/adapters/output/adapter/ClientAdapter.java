package com.bayteq.client.infrastructure.adapters.output.adapter;

import com.bayteq.client.application.ports.output.IClientRepository;
import com.bayteq.client.domain.models.Client;
import com.bayteq.client.infrastructure.adapters.output.entities.ClientEntity;
import com.bayteq.client.infrastructure.adapters.output.mapper.ClientEntityMapper;
import com.bayteq.client.infrastructure.adapters.output.repository.ClientJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ClientAdapter implements IClientRepository {

    private final ClientJpaRepository clientJpaRepository;
    private final ClientEntityMapper mapper;

    public ClientAdapter(ClientJpaRepository clientJpaRepository, ClientEntityMapper mapper) {
        this.clientJpaRepository = clientJpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Client save(Client client) {
        ClientEntity clientEntity = mapper.toEntity(client);
        ClientEntity savedEntity = clientJpaRepository.save(clientEntity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public List<Client> findAll() {
        return clientJpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Client> findById(Long id) {
        return clientJpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public boolean existsById(Long id) {
        return clientJpaRepository.existsById(id);
    }

    @Override
    public boolean existsByDocumentNumber(String documentNumber) {
        return clientJpaRepository.existsByDocumentNumber((documentNumber));
    }

    @Override
    public boolean existsByEmail(String email) {
        return clientJpaRepository.existsByEmail((email));
    }

    @Override
    public Optional<Client> findByDocumentNumber(String documentNumber) {
        return clientJpaRepository.findByDocumentNumber((documentNumber))
                .map(mapper::toDomain);
    }

    @Override
    public Optional<Client> findByEmail(String email) {
        return clientJpaRepository.findByEmail((email))
                .map(mapper::toDomain);
    }
}
