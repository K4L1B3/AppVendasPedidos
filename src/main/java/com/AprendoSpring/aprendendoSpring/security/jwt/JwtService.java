package com.AprendoSpring.aprendendoSpring.security.jwt;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

import com.AprendoSpring.aprendendoSpring.AprendendoSpringApplication;
import com.AprendoSpring.aprendendoSpring.models.Usuario;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
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
        Date data = Date.from(instant);
        
        //Cria um hashmap com as informações que você deseja passar no token
        // HashMap<String, Object> claims = new HashMap<>();
        // claims.put("Email Do Usuario ", "usuario@email.com");
        // claims.put("roles ", "ADMIN");
        // claims.put("login ", usuario.getLogin());
        // claims.put("Expiration Date ", data);

        return Jwts
            .builder()
            .setSubject(usuario.getLogin())
            .setExpiration(data)
            //Para passar propriedades customizadas no token
            // Geralmente o padrão é o Subject e data
            // .setClaims(claims)
            .claim("Email do usuario", "usario@gmail.com")
            .claim("roles", "ADMIN")
            .signWith(SignatureAlgorithm.HS512, chaveAssinatura)
            .compact();

    }

    //Decode de código JWT
    private Claims obterClaims (String token) throws ExpiredJwtException {
        return Jwts
            .parser()
            .setSigningKey(chaveAssinatura)
            .parseClaimsJws(token)
            .getBody();
    }

    //verificar se o token ainda é valido de acordo com a data e hora de expiração
    public boolean tokenValido (String token) {
        try {
            Claims claims = obterClaims(token);
            Date dataExpiracao = claims.getExpiration();
            LocalDateTime data = 
                dataExpiracao.toInstant()
                    .atZone(ZoneId.systemDefault()).toLocalDateTime();
            //Não sendo a hora atual depois da data de expiração logo o token está valido
            return !LocalDateTime.now().isAfter(data);
        } catch (Exception e) {
            return false;
        }
    }

    //Serve para saber quem é o usuário que vai estar logado
    public String obterLoginUsuario (String token) throws ExpiredJwtException{
        return (String) obterClaims(token).getSubject();

    }


    // Para testar se o JWT está funcionando normalmente contendo o 
    // header, payload e a chave de assinatura
    public static void main(String[] args){
        ConfigurableApplicationContext contexto = SpringApplication.run(AprendendoSpringApplication.class);
        JwtService service = contexto.getBean(JwtService.class);
        Usuario usuario = Usuario.builder().login("Fulano").build();
        String token = service.gerarToken(usuario);
        System.out.println(token);

        //Testando para ver se o token está valido
        boolean isTokenValido = service.tokenValido(token);
        System.out.println("O token está valido? " + isTokenValido);

        //Testando para ver qual é o usuário que está logado
        System.out.println(service.obterLoginUsuario(token));



    }

}
