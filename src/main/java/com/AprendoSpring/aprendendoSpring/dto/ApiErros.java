package com.AprendoSpring.aprendendoSpring.dto;

import java.util.Arrays;
import java.util.List;

import lombok.Data;

@Data
public class ApiErros {
    private List<String> errors;

    public ApiErros(List<String> errors){
        this.errors = errors;
    }

    public ApiErros(String mensagemErro){
        this.errors = Arrays.asList(mensagemErro);
    }


}
