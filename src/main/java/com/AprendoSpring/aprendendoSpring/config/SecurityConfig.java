package com.AprendoSpring.aprendendoSpring.config;

import com.AprendoSpring.aprendendoSpring.security.jwt.JwtAuthFilter;
import com.AprendoSpring.aprendendoSpring.security.jwt.JwtService;
import com.AprendoSpring.aprendendoSpring.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import ch.qos.logback.core.encoder.Encoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    
    @Lazy
    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;


    //Criptografar e descriptografar a senha do usuário
    @Bean 
    public PasswordEncoder passwordEncoder(){
        // o Bcrypt é um algoritmo avançado de criptografia
        return new BCryptPasswordEncoder();
        

        // PasswordEncoder passwordEncoder = new PasswordEncoder() {
        

        //     //Recebe a senha original do usuário e retorna a senha criptografada
        //     @Override
        //     public String encode(CharSequence rawPassword) {
        //         // TODO Auto-generated method stub
        //         return rawPassword + "321";
        //     }

        //     //Recebe a senha do usuário e a senha criptografada
        //     @Override
        //     public boolean matches(CharSequence rawPassword, String encodedPassword) {
        //         // TODO Auto-generated method stub
        //         return false;
        //     }
            
        // };
        // return passwordEncoder;
        
    }

    //implementando o método para interceptar as requisições
    @Bean
    public OncePerRequestFilter jwtFilter(){
        return new JwtAuthFilter(jwtService, userService);
    }

    //Maneger Builder configura o usuario para dentro do contexto do spring security
    // @Override
    // protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    //     auth.userDetailsService(userService)    
    //         .passwordEncoder(passwordEncoder());   
    



    // //DEFINIR USER EM MEMÓRIA
    // //    auth.inMemoryAuthentication()
    // //         .passwordEncoder(passwordEncoder())
    // //         .withUser("fulano")
    // //         .password(passwordEncoder().encode("12345678"))
    // //         .roles("USER", "ADMIN");
    // }



    //Http security
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
            //definir quem acessa oque atrásves do parâmetro antMatchers
            //http://localhost:8080/cliente/create
                .antMatchers( HttpMethod.POST, "/api/usuario/**")
                    .permitAll()
                .antMatchers("/api/cliente/**")
                    .hasAnyRole("USER", "ADMIN")
                .antMatchers("/api/produto/**")
                    .hasRole("ADMIN")
                .antMatchers("/api/pedidos/**")
                    .hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
                    // o has role fala que tem que estar autenticado você também pode adicionar: .hasRole("USER")
                    // nisso só seriam permitido pessoas que tivessem a role "USER" podendo 
                    // ser usado também .hasAuthority("MANTER USUARIO") 
                    // também existe o permitAll() que não precisa estar autenticado

                    //cria um formulário de login do spring security ou você pode criar
                        // o seu proprio formulário de login e colocar uma caminho para ele
                        // passando o path dentro do parâmetro em uma pasta que esteja dentro
                        // de resources > static ou templates ficaria algo como ("/meu-login.html")
                        // esse formulário deve ser submetido apenas com o método POST tendo dois 
                        // campos de inputs por ex "user e password" com atributo names 

                        /*
                            <form method="post">
                                <input type="text" name="username">
                                <input type="secret" name="password">
                                <button type="submit"....
                            </form>
                        */
                            // .formLogin();
                            //Para utilizar via insominia
    }


}
