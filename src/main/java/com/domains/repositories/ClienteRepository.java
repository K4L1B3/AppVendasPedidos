package com.AprendoSpring.aprendendoSpring.repositories;

import java.util.Optional;

import com.AprendoSpring.aprendendoSpring.models.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    
    boolean existsByNome(String nome);

    Optional<Cliente> findByNome(String nome);

    Optional<Cliente> findByIdAndNome(Long id, String nome);

    
}
