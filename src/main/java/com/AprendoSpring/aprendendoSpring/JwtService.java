package com.AprendoSpring.aprendendoSpring;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;

import com.AprendoSpring.aprendendoSpring.models.Usuario;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;

@Service
public class JwtService {
    
    @Value ("${security.jwt.expiracao}")
    private String expiracao;

    @Value("${security.jwt.chave-assinatura}")
    private String chaveAssinatura;

    public String gerarToken (Usuario usuario){
        
        Long expString = Long.valueOf(expiracao);
        LocalDateTime DataHoraExpiracao = LocalDateTime.now().plusMinutes(expString);
        Instant instant = DataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant();
        java.util.Date data = Date.from(instant);
        
        //Cria um hashmap com as informações que você deseja passar no token
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("Email Do Usuario", "usuario@email.com");
        claims.put("roles", "ADMIN");
        claims.put("login", usuario.getLogin());

        return Jwts
            .builder()
            .setSubject(usuario.getLogin())
            .setExpiration(data)
            //Para passar propriedades customizadas no token
            // Geralmente o padrão é o Subject e data
            .setClaims(claims)
            .signWith(SignatureAlgorithm.HS512, chaveAssinatura)
            .compact();

    }

    // Para testar se o JWT está funcionando normalmente contendo o 
    // header, payload e a chave de assinatura
    public static void main(String[] args){
        ConfigurableApplicationContext contexto = SpringApplication.run(AprendendoSpringApplication.class);
        JwtService service = contexto.getBean(JwtService.class);
        Usuario usuario = Usuario.builder().login("Fulano").build();
        String token = service.gerarToken(usuario);
        System.out.println(token);
    }

}
