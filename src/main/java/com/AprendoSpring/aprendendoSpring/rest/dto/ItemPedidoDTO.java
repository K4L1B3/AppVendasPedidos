package com.AprendoSpring.aprendendoSpring.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedidoDTO {

    private Long produtoId;

    private int qtd;
    
}
