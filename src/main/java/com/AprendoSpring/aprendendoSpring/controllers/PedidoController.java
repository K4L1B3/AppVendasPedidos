// package com.AprendoSpring.aprendendoSpring.controllers;

// import java.util.List;

// import com.AprendoSpring.aprendendoSpring.models.Pedido;
// import com.AprendoSpring.aprendendoSpring.service.PedidoService;

// import org.apache.catalina.connector.Response;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// @RestController
// @RequestMapping("/pedidos")
// public class PedidoController {
    
//     @Autowired
//     private PedidoService pedidoService;

//     //CREATE AND SAVE
//     @PostMapping("/abrirPedido")
//     public ResponseEntity<Object> savePedido(Pedido pedido){
//         return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.save(pedido));
//     }

//     //List All
//     @GetMapping("/verTodosPedidos")
//     public List<Pedido> findAllPedidos(){
//         return pedidoService.findAll();
//     }

//     //List By Id
//     @GetMapping("/pedidoId/{id}")
//     public ResponseEntity<Object> pedidosPorId(@PathVariable value())

// }
