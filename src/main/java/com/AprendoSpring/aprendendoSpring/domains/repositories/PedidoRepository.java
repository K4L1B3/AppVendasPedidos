package com.AprendoSpring.aprendendoSpring.domains.repositories;


import com.AprendoSpring.aprendendoSpring.domains.models.Pedido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
