package com.AprendoSpring.aprendendoSpring.services;

import java.util.List;
import java.util.Optional;

import com.domains.models.Cliente;
import com.domains.repositories.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {


    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente save(Cliente cliente){
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
