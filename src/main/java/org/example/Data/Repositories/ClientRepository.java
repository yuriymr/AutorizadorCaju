package org.example.Data.Repositories;

import org.example.Application.DTOs.ClientDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<ClientDTO, Long> {
}
