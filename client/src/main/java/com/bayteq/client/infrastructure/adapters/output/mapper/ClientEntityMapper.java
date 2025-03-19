package com.bayteq.client.infrastructure.adapters.output.mapper;

import com.bayteq.client.domain.models.Client;
import com.bayteq.client.infrastructure.adapters.output.entities.ClientEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientEntityMapper {

    ClientEntity toEntity(Client client);
    Client toDomain(ClientEntity entity);
}
