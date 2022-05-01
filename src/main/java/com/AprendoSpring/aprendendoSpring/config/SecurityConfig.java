package com.AprendoSpring.aprendendoSpring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    

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


    //Maneger Builder
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.inMemoryAuthentication()
            .passwordEncoder(passwordEncoder())
            .withUser("fulano")
            .password(passwordEncoder().encode("12345678"))
            .roles("USER");
    }

    //Http security
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
            //definir quem acessa oque atrásves do parâmetro antMatchers
            //http://localhost:8080/cliente/create
                .antMatchers("/cliente/**")
                // tem que estar autenticado você também pode adicionar: .hasRole("USER")
                // nisso só seriam permitido pessoas que tivessem a role "USER" podendo 
                // ser usado também .hasAuthority("MANTER USUARIO") 
                // também existe o permitAll() que não precisa estar autenticado
                    .authenticated()
                        .and()
                        //cria um formulário de login do spring security ou você pode criar
                        // o seu proprio formulário de login e colocar uma caminho para ele
                        // passando o path dentro do parâmetro em uma pasta que esteja dentro
                        // de resources > static ou templates
                            .formLogin() 
    }


}
