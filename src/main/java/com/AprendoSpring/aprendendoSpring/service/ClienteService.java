package com.AprendoSpring.aprendendoSpring.service;

import java.util.List;
import java.util.Optional;

import com.AprendoSpring.aprendendoSpring.models.Cliente;
import com.AprendoSpring.aprendendoSpring.repositories.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {


    @Autowired
    private ClienteRepository clienteRepository;

    public Object save(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> findById(Long id) {
        return clienteRepository.findById(id);
    }

    public boolean existsByNome( String nome) {
        return clienteRepository.existsByNome(nome);
    }

    public void delete(Cliente cliente) {
        clienteRepository.delete(cliente);
    }

    public Optional<Cliente> findByNome(String nome) {
        return clienteRepository.findByNome(nome);
    }

    public Optional<Cliente> findByIdAndNome(Long id, String nome) {
        return clienteRepository.findByIdAndNome(id, nome);
    }        

}
