package com.AprendoSpring.aprendendoSpring.repositories;

import com.AprendoSpring.aprendendoSpring.models.Item_pedido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Item_pedidoRepository extends JpaRepository<Item_pedido, Long>{
    
}
