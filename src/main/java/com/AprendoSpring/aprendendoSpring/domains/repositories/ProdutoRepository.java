package com.AprendoSpring.aprendendoSpring.domains.repositories;

import java.util.Optional;

import com.AprendoSpring.aprendendoSpring.domains.models.Produto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

    boolean existsByNome(String nome);

    Optional<Produto> findByNome(String nome);

    
}
