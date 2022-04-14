package com.AprendoSpring.aprendendoSpring.repositories;

import com.AprendoSpring.aprendendoSpring.models.Produto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
    
}
