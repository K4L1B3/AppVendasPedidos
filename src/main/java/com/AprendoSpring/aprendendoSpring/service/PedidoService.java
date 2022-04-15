package com.AprendoSpring.aprendendoSpring.service;

import java.util.List;
import java.util.Optional;

import com.AprendoSpring.aprendendoSpring.models.Pedido;
import com.AprendoSpring.aprendendoSpring.repositories.PedidoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Object save(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> findById(Long id) {
        return pedidoRepository.findById(id);
    }

    public void delete(Pedido pedido) {
         pedidoRepository.delete(pedido);
    }
    
}
