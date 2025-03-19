package com.bayteq.client.infrastructure.adapters.input.mapper;

import com.bayteq.client.domain.models.Client;
import com.bayteq.client.infrastructure.adapters.input.dto.ClientRequestDTO;
import com.bayteq.client.infrastructure.adapters.input.dto.ClientResponseDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-19T13:00:54-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.13.jar, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class ClientDTOMapperImpl implements ClientDTOMapper {

    @Override
    public Client toClient(ClientRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Client client = new Client();

        client.setName( dto.getName() );
        client.setLastName( dto.getLastName() );
        client.setDocumentNumber( dto.getDocumentNumber() );
        client.setEmail( dto.getEmail() );
        client.setPhoneNumber( dto.getPhoneNumber() );
        client.setBirthDate( dto.getBirthDate() );
        client.setAddress( dto.getAddress() );

        return client;
    }

    @Override
    public ClientResponseDTO toResponseDTO(Client client) {
        if ( client == null ) {
            return null;
        }

        ClientResponseDTO clientResponseDTO = new ClientResponseDTO();

        clientResponseDTO.setId( client.getId() );
        clientResponseDTO.setName( client.getName() );
        clientResponseDTO.setLastName( client.getLastName() );
        clientResponseDTO.setDocumentNumber( client.getDocumentNumber() );
        clientResponseDTO.setEmail( client.getEmail() );
        clientResponseDTO.setPhoneNumber( client.getPhoneNumber() );
        clientResponseDTO.setBirthDate( client.getBirthDate() );
        clientResponseDTO.setAddress( client.getAddress() );
        clientResponseDTO.setActive( client.isActive() );

        return clientResponseDTO;
    }
}
