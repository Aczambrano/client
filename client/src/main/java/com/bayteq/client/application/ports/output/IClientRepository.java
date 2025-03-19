package com.bayteq.client.application.ports.output;

import com.bayteq.client.domain.models.Client;

import java.util.List;
import java.util.Optional;

public interface IClientRepository {
    Client save(Client client);
    List<Client> findAll();
    Optional<Client> findById(Long id);
    boolean existsById(Long id);
    boolean existsByDocumentNumber(String documentNumber);
    boolean existsByEmail(String email);
    Optional<Client> findByDocumentNumber(String documentNumber);
    Optional<Client> findByEmail(String email);
}
