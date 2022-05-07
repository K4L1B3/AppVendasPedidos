package com.AprendoSpring.aprendendoSpring.services;

import java.net.PasswordAuthentication;

import javax.transaction.Transactional;

import com.AprendoSpring.aprendendoSpring.exception.SenhaInvalidaException;
import com.AprendoSpring.aprendendoSpring.models.Usuario;
import com.AprendoSpring.aprendendoSpring.repositories.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    //SALVAR usuário
    @Transactional
    public Object save(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public UserDetails autenticar(Usuario usuario) {
        UserDetails user = loadUserByUsername(usuario.getLogin());
        boolean senhasBatem = encoder.matches(usuario.getSenha(), user.getPassword());
        if (senhasBatem) {
            return user;
        } else {
            throw new SenhaInvalidaException();
        }
    }

    //Carregar o usuário para da base de dados através do login dele
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByLogin(username)
            .orElseThrow(() -> new UsernameNotFoundException("ERROR: Usuário não existe na base de dados!"));

        String[] roles = usuario.isAdmin() ? new String[]{"ADMIN", "USER"} : new String[]{"USER"};

        return User
            .builder()
            .username(usuario.getLogin())
            .password(usuario.getSenha())
            .build();

    }
}



