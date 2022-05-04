package com.AprendoSpring.aprendendoSpring.controllers;

import java.util.List;
import java.util.Optional;

import com.AprendoSpring.aprendendoSpring.models.Produto;
import com.AprendoSpring.aprendendoSpring.services.ProdutoService;

import org.apache.catalina.connector.Response;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {
    

    @Autowired
    private ProdutoService produtoService;

    @PostMapping("/criarProduto")
    public ResponseEntity<Object> salvarProduto(Produto produto){
        if(produtoService.existsByNome(produto.getNome())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("ERRO: O produto já está cadastrado!");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.save(produto));
    }

    @GetMapping("/listarTodosProdutos")
    public List<Produto> findAllProdutos(){
        return produtoService.findAll();
    }

    @GetMapping("/listarProdutoPorNome/{nome}")
    public ResponseEntity<Object> findByProdutoNome(@PathVariable (value = "nome") String nome){
        Optional<Produto> produtoOptional = produtoService.findByNome(nome);
        if (!produtoOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERRO: O Produto não foi encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(produtoOptional.get());
    }

    @PutMapping("/produtoUpdate/{id}")
    public ResponseEntity<Object> produtoUpdate(@PathVariable (value = "id") Long id, @RequestBody Produto produto){
        Optional<Produto> produtoOptional = produtoService.findById(id);
       
        if(!produtoOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERRO: Esse Produto não existe para ser atualizado.");
        }

        var atualizarProduto = new Produto();
        BeanUtils.copyProperties(produto, atualizarProduto);
        produto.setId(produtoOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.save(produto));
    }

    @DeleteMapping("/deletarProduto/{id}")
    public ResponseEntity<Object> deletarProduto(@PathVariable (value = "id") Long id){
        Optional<Produto> produtoOptional = produtoService.findById(id);
        if (!produtoOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERROR: Produto não foi encontrado");
        }
        produtoService.delete(produtoOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Produto deletado com sucesso!");

    }



}

