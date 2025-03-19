package com.bayteq.client.domain.model;
import com.bayteq.client.domain.models.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    private Client client1;
    private Client client2;

    @BeforeEach
    void setUp() {
        client1 = new Client(1L, "John", "Doe", "1234567890", "john.doe@example.com",
                "123456789", LocalDate.of(1990, 1, 1), "123 Main St", true);
        client2 = new Client(1L, "John", "Doe", "1234567890", "john.doe@example.com",
                "123456789", LocalDate.of(1990, 1, 1), "123 Main St", true);
    }

    @Test
    void testGettersAndSetters() {
        Client client = new Client();
        client.setId(2L);
        client.setName("Jane");
        client.setLastName("Doe");
        client.setDocumentNumber("0987654321");
        client.setEmail("jane.doe@example.com");
        client.setPhoneNumber("987654321");
        client.setBirthDate(LocalDate.of(1995, 5, 15));
        client.setAddress("456 Elm St");
        client.setActive(false);

        assertEquals(2L, client.getId());
        assertEquals("Jane", client.getName());
        assertEquals("Doe", client.getLastName());
        assertEquals("0987654321", client.getDocumentNumber());
        assertEquals("jane.doe@example.com", client.getEmail());
        assertEquals("987654321", client.getPhoneNumber());
        assertEquals(LocalDate.of(1995, 5, 15), client.getBirthDate());
        assertEquals("456 Elm St", client.getAddress());
        assertFalse(client.isActive());
    }

    @Test
    void testEqualsAndHashCode() {
        assertEquals(client1, client2);
        assertEquals(client1.hashCode(), client2.hashCode());
    }

    @Test
    void testNotEquals() {
        Client differentClient = new Client(2L, "Jane", "Smith", "1111111111", "jane.smith@example.com",
                "222222222", LocalDate.of(2000, 2, 2), "789 Pine St", false);
        assertNotEquals(client1, differentClient);
    }
}
