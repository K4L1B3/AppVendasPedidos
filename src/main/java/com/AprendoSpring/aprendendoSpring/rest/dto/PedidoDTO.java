package com.AprendoSpring.aprendendoSpring.rest.dto;

import java.math.BigDecimal;
import java.util.List;

import com.AprendoSpring.aprendendoSpring.models.Pedido;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {

    private Long clienteId;

    private BigDecimal total;

    private List<ItemPedidoDTO> items;


}
