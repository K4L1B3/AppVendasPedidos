package com.AprendoSpring.aprendendoSpring.rest.controllers;

import javax.validation.Valid;

import com.AprendoSpring.aprendendoSpring.domains.models.Usuario;
import com.AprendoSpring.aprendendoSpring.exception.SenhaInvalidaException;
import com.AprendoSpring.aprendendoSpring.rest.dto.CredenciaisDTO;
import com.AprendoSpring.aprendendoSpring.rest.dto.TokenDTO;
import com.AprendoSpring.aprendendoSpring.security.jwt.JwtService;
import com.AprendoSpring.aprendendoSpring.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
    
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;


    //Método de criação de usuário
    @PostMapping("/signup")
    public ResponseEntity<Usuario> salvarUser(@RequestBody @Valid Usuario usuario){
        //Criptografar senha do usuário e salva-la no usuário
        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
        System.out.println(senhaCriptografada);
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(usuario));
    }


    //Método de autenticação
    @PostMapping("/login")
    public TokenDTO autenticar(@RequestBody CredenciaisDTO credenciaisDTO){
        try {
            Usuario usuario = Usuario.builder()
                .login(credenciaisDTO.getLogin())
                .senha(credenciaisDTO.getSenha())
                    .build();
            UserDetails usuarioAutenticado = userService.autenticar(usuario);
            String token = jwtService.gerarToken(usuario);
            return new TokenDTO(usuario.getLogin(), token);
        } catch (UsernameNotFoundException | SenhaInvalidaException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
    }
    }
}
