package com.AprendoSpring.aprendendoSpring.repositories;

import com.AprendoSpring.aprendendoSpring.models.Pedido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
   
    Pedido salvar (Pedido dto);

}
