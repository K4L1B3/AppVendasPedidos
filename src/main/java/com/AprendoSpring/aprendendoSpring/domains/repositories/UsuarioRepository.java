package com.AprendoSpring.aprendendoSpring.domains.repositories;

import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.AprendoSpring.aprendendoSpring.domains.models.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
    Optional<Usuario> findByLogin(String login);

   


}
