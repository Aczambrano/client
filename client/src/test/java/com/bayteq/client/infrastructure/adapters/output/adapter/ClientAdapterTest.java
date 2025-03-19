package com.bayteq.client.infrastructure.adapters.output.adapter;

import com.bayteq.client.domain.models.Client;
import com.bayteq.client.infrastructure.adapters.output.entities.ClientEntity;
import com.bayteq.client.infrastructure.adapters.output.mapper.ClientEntityMapper;
import com.bayteq.client.infrastructure.adapters.output.repository.ClientJpaRepository;
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
class ClientAdapterTest {

    @Mock
    private ClientJpaRepository clientJpaRepository;

    @Mock
    private ClientEntityMapper mapper;

    @InjectMocks
    private ClientAdapter clientAdapter;

    private Client client;
    private ClientEntity clientEntity;

    @BeforeEach
    void setUp() {
        client = new Client(1L, "John", "Doe", "1234567890", "john.doe@example.com",
                "1234567890", LocalDate.of(1990, 1, 1), "123 Main St", true);

        clientEntity = new ClientEntity();
        clientEntity.setId(1L);
        clientEntity.setName("John");
        clientEntity.setLastName("Doe");
        clientEntity.setDocumentNumber("1234567890");
        clientEntity.setEmail("john.doe@example.com");
        clientEntity.setPhoneNumber("1234567890");
        clientEntity.setBirthDate(LocalDate.of(1990, 1, 1));
        clientEntity.setAddress("123 Main St");
        clientEntity.setActive(true);
    }

    @Test
    void testSave() {
        when(mapper.toEntity(client)).thenReturn(clientEntity);
        when(clientJpaRepository.save(clientEntity)).thenReturn(clientEntity);
        when(mapper.toDomain(clientEntity)).thenReturn(client);

        Client savedClient = clientAdapter.save(client);

        assertNotNull(savedClient);
        assertEquals(client.getId(), savedClient.getId());
        verify(clientJpaRepository).save(clientEntity);
    }

    @Test
    void testFindAll() {
        when(clientJpaRepository.findAll()).thenReturn(List.of(clientEntity));
        when(mapper.toDomain(clientEntity)).thenReturn(client);

        List<Client> clients = clientAdapter.findAll();

        assertFalse(clients.isEmpty());
        assertEquals(1, clients.size());
        verify(clientJpaRepository).findAll();
    }

    @Test
    void testFindById() {
        when(clientJpaRepository.findById(1L)).thenReturn(Optional.of(clientEntity));
        when(mapper.toDomain(clientEntity)).thenReturn(client);

        Optional<Client> foundClient = clientAdapter.findById(1L);

        assertTrue(foundClient.isPresent());
        assertEquals(client.getId(), foundClient.get().getId());
        verify(clientJpaRepository).findById(1L);
    }

    @Test
    void testExistsById() {
        when(clientJpaRepository.existsById(1L)).thenReturn(true);

        boolean exists = clientAdapter.existsById(1L);

        assertTrue(exists);
        verify(clientJpaRepository).existsById(1L);
    }

    @Test
    void testExistsByDocumentNumber() {
        when(clientJpaRepository.existsByDocumentNumber("1234567890")).thenReturn(true);

        boolean exists = clientAdapter.existsByDocumentNumber("1234567890");

        assertTrue(exists);
        verify(clientJpaRepository).existsByDocumentNumber("1234567890");
    }

    @Test
    void testExistsByEmail() {
        when(clientJpaRepository.existsByEmail("john.doe@example.com")).thenReturn(true);

        boolean exists = clientAdapter.existsByEmail("john.doe@example.com");

        assertTrue(exists);
        verify(clientJpaRepository).existsByEmail("john.doe@example.com");
    }

    @Test
    void testFindByDocumentNumber() {
        when(clientJpaRepository.findByDocumentNumber("1234567890")).thenReturn(Optional.of(clientEntity));
        when(mapper.toDomain(clientEntity)).thenReturn(client);

        Optional<Client> foundClient = clientAdapter.findByDocumentNumber("1234567890");

        assertTrue(foundClient.isPresent());
        assertEquals(client.getDocumentNumber(), foundClient.get().getDocumentNumber());
        verify(clientJpaRepository).findByDocumentNumber("1234567890");
    }

    @Test
    void testFindByEmail() {
        when(clientJpaRepository.findByEmail("john.doe@example.com")).thenReturn(Optional.of(clientEntity));
        when(mapper.toDomain(clientEntity)).thenReturn(client);

        Optional<Client> foundClient = clientAdapter.findByEmail("john.doe@example.com");

        assertTrue(foundClient.isPresent());
        assertEquals(client.getEmail(), foundClient.get().getEmail());
        verify(clientJpaRepository).findByEmail("john.doe@example.com");
    }
}
