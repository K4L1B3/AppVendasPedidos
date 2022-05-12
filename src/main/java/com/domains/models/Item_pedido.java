package com.domains.models;

import java.math.BigDecimal;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "item_pedido")
public class Item_pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 20)
    private int qtd;

    @ManyToOne
    @JoinColumn( nullable = false, name = "id_pedido")
    private Pedido id_Pedido;
    
    
    @ManyToOne
    @JoinColumn(nullable = false, name = "id_produto")
    private Produto id_Produto;


    
}
