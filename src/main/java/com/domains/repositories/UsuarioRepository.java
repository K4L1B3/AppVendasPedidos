package com.domains.repositories;

import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.domains.models.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
    Optional<Usuario> findByLogin(String login);

   


}
