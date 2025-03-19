package com.bayteq.client.infrastructure.adapters.input.controller;

import com.bayteq.client.application.ports.input.IClientService;
import com.bayteq.client.domain.models.Client;
import com.bayteq.client.infrastructure.adapters.input.dto.ClientRequestDTO;
import com.bayteq.client.infrastructure.adapters.input.dto.ClientResponseDTO;
import com.bayteq.client.infrastructure.adapters.input.mapper.ClientDTOMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientControllerTest {

    @Mock
    private IClientService clientService;

    @Mock
    private ClientDTOMapper mapper;

    @InjectMocks
    private ClientController clientController;

    private Client client;
    private ClientRequestDTO clientRequestDTO;
    private ClientResponseDTO clientResponseDTO;

    @BeforeEach
    void setUp() {
        client = new Client(1L, "John", "Doe", "1234567890", "john.doe@example.com",
                "1234567890", LocalDate.of(1990, 1, 1), "123 Main St", true);

        clientRequestDTO = new ClientRequestDTO();
        clientRequestDTO.setName("John");
        clientRequestDTO.setLastName("Doe");
        clientRequestDTO.setDocumentNumber("1234567890");
        clientRequestDTO.setEmail("john.doe@example.com");
        clientRequestDTO.setPhoneNumber("1234567890");
        clientRequestDTO.setBirthDate(LocalDate.of(1990, 1, 1));
        clientRequestDTO.setAddress("123 Main St");

        clientResponseDTO = new ClientResponseDTO();
        clientResponseDTO.setId(1L);
        clientResponseDTO.setName("John");
        clientResponseDTO.setLastName("Doe");
        clientResponseDTO.setDocumentNumber("1234567890");
        clientResponseDTO.setEmail("john.doe@example.com");
        clientResponseDTO.setPhoneNumber("1234567890");
        clientResponseDTO.setBirthDate(LocalDate.of(1990, 1, 1));
        clientResponseDTO.setAddress("123 Main St");
        clientResponseDTO.setActive(true);
    }


    @Test
    void testCreateClient() {
        when(mapper.toClient(clientRequestDTO)).thenReturn(client);
        when(clientService.createClient(client)).thenReturn(client);
        when(mapper.toResponseDTO(client)).thenReturn(clientResponseDTO);

        ResponseEntity<ClientResponseDTO> response = clientController.createClient(clientRequestDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(clientResponseDTO, response.getBody());
    }

    @Test
    void testGetAllClients() {
        when(clientService.getAllClients()).thenReturn(List.of(client));
        when(mapper.toResponseDTO(client)).thenReturn(clientResponseDTO);

        ResponseEntity<List<ClientResponseDTO>> response = clientController.getAllClients();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(response.getBody().isEmpty());
        assertEquals(1, response.getBody().size());
        assertEquals(clientResponseDTO, response.getBody().get(0));
    }

    @Test
    void testGetClientById_Found() {
        when(clientService.getClientById(1L)).thenReturn(Optional.of(client));
        when(mapper.toResponseDTO(client)).thenReturn(clientResponseDTO);

        ResponseEntity<ClientResponseDTO> response = clientController.getClientById(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clientResponseDTO, response.getBody());
    }

    @Test
    void testGetClientById_NotFound() {
        when(clientService.getClientById(1L)).thenReturn(Optional.empty());

        ResponseEntity<ClientResponseDTO> response = clientController.getClientById(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testUpdateClient() {
        when(mapper.toClient(clientRequestDTO)).thenReturn(client);
        when(clientService.updateClient(1L, client)).thenReturn(client);
        when(mapper.toResponseDTO(client)).thenReturn(clientResponseDTO);

        ResponseEntity<ClientResponseDTO> response = clientController.updateClient(1L, clientRequestDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clientResponseDTO, response.getBody());
    }
}
