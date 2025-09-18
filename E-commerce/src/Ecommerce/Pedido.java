package Ecommerce;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private Customer customer;

    private List<ItensPedidos> itens = new ArrayList<>();

    public Pedido(Customer customer){
        this.customer = customer;
    }
    public void adicionarItem(product produto, int quantidade) {
        ItensPedidos novoItem = new ItensPedidos(produto, quantidade);
        this.itens.add(novoItem);
    }
}

// adicionar as formar de calcular frete adcionar isso no valor das coisas e arrumar as formar de pagamento arrumar clientes, arrumar estoque e fazer o menu interativo na main posso fazer por maquina de estados

/*

    ▪ Composição (relação “parte-todo” forte)
    ▪ Pedido tem ItemPedido (lista interna).
    ▪ Se Pedido for removido, seus ItemPedido também são — vida atrelada ao todo.

 */