package com.bayteq.client.infrastructure.adapters.output.mapper;

import com.bayteq.client.domain.models.Client;
import com.bayteq.client.infrastructure.adapters.output.entities.ClientEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-19T12:41:28-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.13.jar, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class ClientEntityMapperImpl implements ClientEntityMapper {

    @Override
    public ClientEntity toEntity(Client client) {
        if ( client == null ) {
            return null;
        }

        ClientEntity clientEntity = new ClientEntity();

        clientEntity.setId( client.getId() );
        clientEntity.setName( client.getName() );
        clientEntity.setLastName( client.getLastName() );
        clientEntity.setDocumentNumber( client.getDocumentNumber() );
        clientEntity.setEmail( client.getEmail() );
        clientEntity.setPhoneNumber( client.getPhoneNumber() );
        clientEntity.setBirthDate( client.getBirthDate() );
        clientEntity.setAddress( client.getAddress() );
        clientEntity.setActive( client.isActive() );

        return clientEntity;
    }

    @Override
    public Client toDomain(ClientEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Client client = new Client();

        client.setId( entity.getId() );
        client.setName( entity.getName() );
        client.setLastName( entity.getLastName() );
        client.setDocumentNumber( entity.getDocumentNumber() );
        client.setEmail( entity.getEmail() );
        client.setPhoneNumber( entity.getPhoneNumber() );
        client.setBirthDate( entity.getBirthDate() );
        client.setAddress( entity.getAddress() );
        client.setActive( entity.isActive() );

        return client;
    }
}
