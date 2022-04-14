package com.AprendoSpring.aprendendoSpring.service;

import com.AprendoSpring.aprendendoSpring.repositories.PedidoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    
}
