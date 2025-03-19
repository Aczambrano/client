package com.bayteq.client.infrastructure.adapters.output.repository;

import com.bayteq.client.infrastructure.adapters.output.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientJpaRepository extends JpaRepository<ClientEntity, Long> {
    boolean existsByDocumentNumber(String documentNumber);

    boolean existsByEmail(String email);

    Optional<ClientEntity> findByDocumentNumber(String documentNumber);

    Optional<ClientEntity> findByEmail(String email);
}
