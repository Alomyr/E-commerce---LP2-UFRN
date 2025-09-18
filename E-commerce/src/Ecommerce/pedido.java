package Ecommerce;

import java.util.ArrayList;
import java.util.List;

public class pedido {

    private Customer customer;

    private List<ItensPedidos> itens = new ArrayList<>();

    public pedido(){
        this.customer = customer;
    }
    public void adicionarItem(product produto, int quantidade) {
        ItensPedidos novoItem = new ItensPedidos(produto, quantidade);
        this.itens.add(novoItem);
    }

}
/*

    ▪ Composição (relação “parte-todo” forte)
    ▪ Pedido tem ItemPedido (lista interna).
    ▪ Se Pedido for removido, seus ItemPedido também são — vida atrelada ao todo.

 */