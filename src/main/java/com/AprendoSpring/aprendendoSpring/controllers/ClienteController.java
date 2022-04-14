package com.AprendoSpring.aprendendoSpring.controllers;

import java.util.List;
import java.util.Optional;

import com.AprendoSpring.aprendendoSpring.models.Cliente;
import com.AprendoSpring.aprendendoSpring.service.ClienteService;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // SAVE AND CREATE
    @PostMapping("/create")
    public ResponseEntity<Object> save(@RequestBody Cliente cliente){
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.save(cliente));
    }

    //LIST ALL 
    @GetMapping("/all")
    public List<Cliente> findAllClientes(){
        return clienteService.findAll();
    }

    //FIND BY ID
    @GetMapping("/ById/{id}")
    public ResponseEntity<Object> findByIdClientes(@PathVariable (value = "id") Long id){
        Optional<Cliente> clienteOptional = clienteService.findById(id);
        if(!clienteOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Clinete não foi encontrado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(clienteOptional.get());

    }
    
}
