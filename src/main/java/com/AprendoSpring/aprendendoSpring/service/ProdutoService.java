package com.AprendoSpring.aprendendoSpring.service;

import java.util.List;
import java.util.Optional;

import com.AprendoSpring.aprendendoSpring.models.Produto;
import com.AprendoSpring.aprendendoSpring.repositories.ProdutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {


    @Autowired
    private ProdutoRepository produtoRepository;


    public boolean existsByNome(String nome) {
        return produtoRepository.existsByNome(nome);
    }

    public Object save(Produto produto) {
        return produtoRepository.save(produto);
    }

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> findByNome(String nome) {
        return produtoRepository.findByNome(nome);
    }

    public Optional<Produto> findById(Long id) {
        return produtoRepository.findById(id);
    }

    public void delete(Produto produto) {
        produtoRepository.delete(produto);
    }
    
}
