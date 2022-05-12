package com.AprendoSpring.aprendendoSpring.rest.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import com.AprendoSpring.aprendendoSpring.dto.PedidoDTO;
import com.AprendoSpring.aprendendoSpring.models.Pedido;
import com.AprendoSpring.aprendendoSpring.services.PedidoService;

import org.apache.catalina.connector.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pedido")
public class PedidoController {
    
    @Autowired
    private PedidoService pedidoService;

        //CREATE AND SAVE
    @PostMapping("/abrirPedido")
    public ResponseEntity<Object> savePedido(PedidoDTO pedidoDTO){
        // Pedido pedido = pedidoService.save(pedidoDTO);
		// return pedido.getId();

        var pedido = new Pedido();
        BeanUtils.copyProperties( pedidoDTO, pedido);
        pedido.setData_pedido(LocalDate.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.save(pedido));
    }

    

    // //CREATE AND SAVE
    // @PostMapping("/abrirPedido")
    // public ResponseEntity<Object> savePedido(Pedido pedido){
    //     return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.save(pedido));
    // }

    // //List All
    // @GetMapping("/verTodosPedidos")
    // public List<Pedido> findAllPedidos(){
    //     return pedidoService.findAll();
    // }

    // //List By Id
    // @GetMapping("/pedidoId/{id}")
    // public ResponseEntity<Object> pedidosPorId(@PathVariable (value = "id") Long id){
    //     Optional<Pedido> pedidoOptional = pedidoService.findById(id);

    //     if(!pedidoOptional.isPresent()){
    //         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido não existe");
    //     } else{
    //         return ResponseEntity.status(HttpStatus.OK).body(pedidoOptional.get());
    //     }
    // }

    // // UPDATE
    // @PutMapping("/pedidoUpdate/{id}")
    // public ResponseEntity<Object> updatePedidos(@PathVariable (value = "id") Long id, @RequestBody Pedido pedido){
    //     Optional<Pedido> pedidoOptional = pedidoService.findById(id);
    //     if(!pedidoOptional.isPresent()){
    //         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido não encontrado");
    //     }else{

    //         var atualizarPedido = new Pedido();
    //         BeanUtils.copyProperties(pedido, atualizarPedido);
    //         pedido.setId(pedidoOptional.get().getId());
    //         return ResponseEntity.status(HttpStatus.OK).body(pedidoService.save(pedido)); 
    //     }
    // }

    // // DELETE
    // @DeleteMapping("pedidoDel/{id}")
    // public ResponseEntity<Object> deletePedido(@PathVariable (value = "id")Long id){
    //     Optional<Pedido> pedidoOptional = pedidoService.findById(id);
    //     if(!pedidoOptional.isPresent()){
    //         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido não existe!");
    //     }else {
    //         return ResponseEntity.status(HttpStatus.OK).body("Pedido deletado com sucesso!");
    //     }
    // }

}
