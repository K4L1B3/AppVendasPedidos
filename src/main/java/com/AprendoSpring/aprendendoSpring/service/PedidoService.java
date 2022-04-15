package com.AprendoSpring.aprendendoSpring.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.AprendoSpring.aprendendoSpring.dto.ItemPedidoDTO;
import com.AprendoSpring.aprendendoSpring.dto.PedidoDTO;
import com.AprendoSpring.aprendendoSpring.exception.PedidoServiceExcepetion;
import com.AprendoSpring.aprendendoSpring.models.Cliente;
import com.AprendoSpring.aprendendoSpring.models.Item_pedido;
import com.AprendoSpring.aprendendoSpring.models.Pedido;
import com.AprendoSpring.aprendendoSpring.repositories.ClienteRepository;
import com.AprendoSpring.aprendendoSpring.repositories.PedidoRepository;
import com.AprendoSpring.aprendendoSpring.repositories.ProdutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    //SALVANDO PEDIDO
    public Pedido save(PedidoDTO pedidoDTO) {
       
        Long idCliente = pedidoDTO.getClienteId();
        Cliente cliente = clienteRepository
            .findById(idCliente)
            .orElseThrow(() -> new PedidoServiceExcepetion("ERRO: Código de cliente inválido"){

        });

        Pedido pedido = new Pedido();
        pedido.setTotal(pedidoDTO.getTotal());
        pedido.setData_pedido(LocalDate.now());
        pedido.setCliente_id(cliente);
       return null;
    }

    //CONVERTER ITEMS EM ITEMS PEDIDO
    private List<Item_pedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items){
        if(items.isEmpty()){
            throw new PedidoServiceExcepetion("Não é possível realizar um pedido sem items!");
        }
        
        return items
        .stream()
        .map(pedidoDTO -> {
            Long produtoId = pedidoDTO.getProdutoId();
            produtoRepository
                .findById(produtoId)
                .orElseThrow(
                    () -> new PedidoServiceExcepetion(
                        "ERRO: Código de cliente inválido"
                        ));
       

            Item_pedido itemPedido = new Item_pedido();
            itemPedido.setQtd(pedidoDTO);
            itemPedido.setId_Pedido(pedido);
            return itemPedido;
        }).collect(Collectors.toList());
    }




    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> findById(Long id) {
        return pedidoRepository.findById(id);
    }

    public void delete(Pedido pedido) {
         pedidoRepository.delete(pedido);
    }
  

}
