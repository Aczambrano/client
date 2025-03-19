package com.bayteq.client.infrastructure.adapters.input.mapper;

import com.bayteq.client.domain.models.Client;
import com.bayteq.client.infrastructure.adapters.input.dto.ClientRequestDTO;
import com.bayteq.client.infrastructure.adapters.input.dto.ClientResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientDTOMapper {
    Client toClient(ClientRequestDTO dto);
    ClientResponseDTO toResponseDTO(Client client);
}
