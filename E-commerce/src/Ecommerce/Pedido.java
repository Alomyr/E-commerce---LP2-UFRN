package Ecommerce;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private Customer customer;
    private List<ItensPedidos> itens = new ArrayList<>();
    private Estoque estoque;

    public Pedido(Customer customer){
        this.customer = customer;
    }
    public void adicionarItem(product produto, int quantidade) {
        ItensPedidos novoItem = new ItensPedidos(produto, quantidade);
        this.itens.add(novoItem);
    }
    public double calcularTotal(){
        double total=0.0;
         for (ItensPedidos item : itens) {
            total += item.getSubtotal();
        }
        return total;
    }
      public double calcularFreteTotal() {
        double freteTotal = 0.0;
        for (ItensPedidos item : itens) {
            // Chama o método frete() de cada produto (Polimorfismo!)
            freteTotal += item.getProduto().frete();
        }
        return freteTotal;
    }
    private void darBaixaNoEstoque() {
        System.out.println("Dando baixa nos itens do estoque...");
        for (ItensPedidos item : itens) {
            this.estoque.removerItensPorQuantidade(item.getProduto().getCode(), item.getqtd());
        }
    }
}