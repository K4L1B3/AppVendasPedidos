package com.AprendoSpring.aprendendoSpring.rest.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.AprendoSpring.aprendendoSpring.domains.models.Cliente;
import com.AprendoSpring.aprendendoSpring.services.ClienteService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // SAVE AND CREATE
    @PostMapping("/create")
    public ResponseEntity<Object> save(@RequestBody @Valid Cliente cliente){
        if(clienteService.existsByNome(cliente.getNome())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Esse nome já foi cadastrado");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.save(cliente));
    }

    //LIST ALL 
    @GetMapping("/all")
    public List<Cliente> findAllClientes(){
        return clienteService.findAll();
    }

    // //FIND BY ID
    // @GetMapping("/FindById/{id}")
    // public ResponseEntity<Object> findByIdClientes(@PathVariable (value = "id") Long id){
    //     Optional<Cliente> clienteOptional = clienteService.findById(id);
    //     if(!clienteOptional.isPresent()) {
    //         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Clinete não foi encontrado");
    //     }

    //     return ResponseEntity.status(HttpStatus.OK).body(clienteOptional.get());

    // }

    //  //FIND BY name
    //  @GetMapping("/FindByName/{name}")
    //  public ResponseEntity<Object> findByIdClientesName(@PathVariable (value = "name") String nome){
    //      Optional<Cliente> clienteOptional = clienteService.findByNome(nome);
    //      if(!clienteOptional.isPresent()) {
    //          return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Clinete não foi encontrado");
    //      }
 
    //      return ResponseEntity.status(HttpStatus.OK).body(clienteOptional.get());
 
    //  }

    //FindBY Name and Id
    @GetMapping("/FindBy")
    public ResponseEntity<Object> findBy(@RequestParam (required = false) String nome, @RequestParam (required = false) Long id){
        
        Optional<Cliente> clienteOptional = Optional.empty(); 
        // consultar por nome
        if(id == null && nome != null){
            clienteOptional = clienteService.findByNome(nome);
           return ResponseEntity.status(HttpStatus.OK).body(clienteOptional.get());

        // consultar por id
        } else if (id != null && nome == null) {
            clienteOptional = clienteService.findById(id);
           return ResponseEntity.status(HttpStatus.OK).body(clienteOptional.get());

        // consultar por nome e id
        } else if (id != null && nome != null) {
            clienteOptional = clienteService.findByIdAndNome(id, nome);
            return ResponseEntity.status(HttpStatus.OK).body(clienteOptional.get());
        
        } else {
            // retorna o 204 - NO CONTENT pq nenhum filtro de pesquisa foi informado
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Nenhum campo de pesquisa informado");
        }
    }



    //UPDATE
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateCliente(@PathVariable (value = "id")Long id, @RequestBody Cliente cliente){

        Optional<Cliente> clienteOptional = clienteService.findById(id);
        if(!clienteOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado!");
        }

        var AtualizarCliente = new Cliente();
        BeanUtils.copyProperties(cliente, AtualizarCliente);
        cliente.setId(clienteOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.save(cliente));
        
    }

    //DELETE
    @DeleteMapping("del/{id}")
    public ResponseEntity<Object> deleteCliente(@PathVariable (value = "id")Long id){
        Optional<Cliente> clienteOptional = clienteService.findById(id);
        if (!clienteOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: usuário não encontrado ou não cadastrado");
        }

        clienteService.delete(clienteOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Cliente deletado com sucesso!");
    }


    
}
