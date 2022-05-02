package com.AprendoSpring.aprendendoSpring.service;

import java.net.PasswordAuthentication;

import org.springframework.beans.factory.annotation.Autowired;
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

    //Carregar o usuário para a base de dados
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if(!username.equals("cicrano")){
            throw new UsernameNotFoundException("Usuário não existe!");
        }

        return User
                .builder()
                .username("cicrano")
                .password(encoder.encode("1234"))
                .roles("USER", "ADMIN")
                .build();
    }
    
}
