package com.bayteq.client.application.ports.input;

import com.bayteq.client.domain.models.Client;

import java.util.List;
import java.util.Optional;

public interface IClientService {
    Client createClient(Client client);
    List<Client> getAllClients();
    Optional<Client> getClientById(Long id);
    Client updateClient(Long id, Client client);
}
