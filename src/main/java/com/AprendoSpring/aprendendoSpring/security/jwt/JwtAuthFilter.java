package com.AprendoSpring.aprendendoSpring.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.AprendoSpring.aprendendoSpring.services.UserService;

import org.apache.catalina.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthFilter extends OncePerRequestFilter{

    //Injecao de dependencia via construtor
    private JwtService jwtService;
    private UserService userService;

    public JwtAuthFilter (JwtService jwtService, UserService userService){
        this.jwtService = jwtService;
        this.userService = userService;
    }


    // Configura o usuario para dentro do contexto do spring security
    // Filter chain vai interceptar a requisição e antes de processar a requisição eu estou mandando um usuário

    @Override
    protected void doFilterInternal(
        HttpServletRequest request, 
        HttpServletResponse response, 
        FilterChain filterChain)
            throws ServletException, IOException {
      
        // Pegar o header chamado Authorization que conterá o token
        String authorization = request.getHeader("Authorization");
        
        //Fazendo a verificação da request pelo token
        if(authorization != null && authorization.startsWith("Bearer")){
            // Vai pegar toda a string e dividi-la pelos espaços que são 2 logo depois irá
            // adiciona-la no array na posição 0 = Bearer e na 1 = token
            String token = authorization.split(" ") [1];
            //Apôs isso verifico se o token está valido
            boolean isValid = jwtService.tokenValido(token);

            if (isValid) {
                String loginUsuario = jwtService.obterLoginUsuario(token);
                //Aqui carregarei o usuário com todas as suas permissões
                UserDetails usuario =  userService.loadUserByUsername(loginUsuario);
                // Colocando o usuario dentro do contexto do spring security
                UsernamePasswordAuthenticationToken user = 
                    new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                // Indica ao spring security que isso se trata de uma autenticação web
                user.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(user);
            }

            //despacha a requisição
            filterChain.doFilter(request, response);

        }
    }
}
