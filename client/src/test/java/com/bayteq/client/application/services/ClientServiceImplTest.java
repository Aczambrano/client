package com.bayteq.client.application.services;

import com.bayteq.client.application.ports.output.IClientRepository;
import com.bayteq.client.domain.exception.ClientDocumentAlreadyExistsException;
import com.bayteq.client.domain.exception.ClientEmailAlreadyExistsException;
import com.bayteq.client.domain.exception.ClientNotFoundException;
import com.bayteq.client.domain.models.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceImplTest {

    @Mock
    private IClientRepository clientRepository;

    @InjectMocks
    private ClientServiceImpl clientService;

    private Client client;

    @BeforeEach
    void setUp() {
        client = new Client(1L, "John", "Doe", "1234567890", "john.doe@example.com",
                "123456789", LocalDate.of(1990, 1, 1), "123 Main St", true);
    }

    @Test
    void testCreateClient_Success() {
        when(clientRepository.existsByDocumentNumber(client.getDocumentNumber())).thenReturn(false);
        when(clientRepository.existsByEmail(client.getEmail())).thenReturn(false);
        when(clientRepository.save(client)).thenReturn(client);

        Client createdClient = clientService.createClient(client);

        assertNotNull(createdClient);
        assertEquals(client, createdClient);
    }

    @Test
    void testCreateClient_DocumentAlreadyExists() {
        when(clientRepository.existsByDocumentNumber(client.getDocumentNumber())).thenReturn(true);

        assertThrows(ClientDocumentAlreadyExistsException.class, () -> clientService.createClient(client));
    }

    @Test
    void testCreateClient_EmailAlreadyExists() {
        when(clientRepository.existsByDocumentNumber(client.getDocumentNumber())).thenReturn(false);
        when(clientRepository.existsByEmail(client.getEmail())).thenReturn(true);

        assertThrows(ClientEmailAlreadyExistsException.class, () -> clientService.createClient(client));
    }

    @Test
    void testGetAllClients() {
        when(clientRepository.findAll()).thenReturn(List.of(client));

        List<Client> clients = clientService.getAllClients();

        assertFalse(clients.isEmpty());
        assertEquals(1, clients.size());
        assertEquals(client, clients.get(0));
    }

    @Test
    void testGetClientById_Found() {
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

        Optional<Client> foundClient = clientService.getClientById(1L);

        assertTrue(foundClient.isPresent());
        assertEquals(client, foundClient.get());
    }

    @Test
    void testGetClientById_NotFound() {
        when(clientRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Client> foundClient = clientService.getClientById(1L);

        assertFalse(foundClient.isPresent());
    }

    @Test
    void testUpdateClient_Success() {
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        when(clientRepository.findByDocumentNumber(client.getDocumentNumber())).thenReturn(Optional.empty());
        when(clientRepository.findByEmail(client.getEmail())).thenReturn(Optional.empty());
        when(clientRepository.save(client)).thenReturn(client);

        Client updatedClient = clientService.updateClient(1L, client);

        assertNotNull(updatedClient);
        assertEquals(client, updatedClient);
    }

    @Test
    void testUpdateClient_NotFound() {
        when(clientRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ClientNotFoundException.class, () -> clientService.updateClient(1L, client));
    }

    @Test
    void testUpdateClient_DocumentAlreadyExists() {
        Client existingClient = new Client(2L, "Jane", "Doe", "1234567890", "jane.doe@example.com",
                "987654321", LocalDate.of(1995, 5, 15), "456 Elm St", true);
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        when(clientRepository.findByDocumentNumber(client.getDocumentNumber())).thenReturn(Optional.of(existingClient));

        assertThrows(ClientDocumentAlreadyExistsException.class, () -> clientService.updateClient(1L, client));
    }

    @Test
    void testUpdateClient_EmailAlreadyExists() {
        Client existingClient = new Client(2L, "Jane", "Doe", "0987654321", "john.doe@example.com",
                "987654321", LocalDate.of(1995, 5, 15), "456 Elm St", true);
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        when(clientRepository.findByEmail(client.getEmail())).thenReturn(Optional.of(existingClient));

        assertThrows(ClientEmailAlreadyExistsException.class, () -> clientService.updateClient(1L, client));
    }
}
