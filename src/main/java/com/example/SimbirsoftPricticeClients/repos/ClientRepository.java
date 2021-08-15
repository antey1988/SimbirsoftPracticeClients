package com.example.SimbirsoftPricticeClients.repos;

import com.example.SimbirsoftPricticeClients.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query(value = "select c from Client c where c.bankAccount.number = :number")
    Optional<Client> findByBankAccountNumber(@Param(value = "number") String number);
    Optional<Client> findByUuid(UUID uuid);
}
