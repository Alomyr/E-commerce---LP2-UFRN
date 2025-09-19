package Ecommerce;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private Customer customer;
    private List<ItensPedidos> itens = new ArrayList<>();
    private Estoque estoque;

    public Pedido(Customer customer, Estoque estoque){
        this.customer = customer;
        this.estoque = estoque;
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
    public void realizarPagamento(Pagamento pagto) {
        pagto.processar();
        darBaixaNoEstoque();
    }
public void removerItem(String codigoProduto) {
    ItensPedidos itemParaRemover = null;
    for (ItensPedidos item : itens) {
        if (item.getProduto().getCode().equals(codigoProduto)) {
            itemParaRemover = item;
            break;
        }
    }

    if (itemParaRemover != null) {
        this.estoque.aumentarQuantidade(itemParaRemover.getProduto().getCode(), itemParaRemover.getqtd());
        this.itens.remove(itemParaRemover);
        System.out.println("Item com código " + codigoProduto + " removido do pedido.");
    } else {
        System.out.println("Item não encontrado no pedido.");
    }
}

    public void atualizarQuantidade(String codigoProduto, int novaQuantidade) {
        ItensPedidos itemParaAtualizar = null;
        for (ItensPedidos item : itens) {
            if (item.getProduto().getCode().equals(codigoProduto)) {
                itemParaAtualizar = item;
                break;
            }
        }

        if (itemParaAtualizar != null) {
            int quantidadeAnterior = itemParaAtualizar.getqtd();
            int diferenca = novaQuantidade - quantidadeAnterior;

            if (diferenca > 0) {
                this.estoque.removerItensPorQuantidade(codigoProduto, diferenca);
            } else if (diferenca < 0) {
                this.estoque.aumentarQuantidade(codigoProduto, -diferenca);
            }

            if (novaQuantidade > 0) {
                itemParaAtualizar.setqtd(novaQuantidade);
                System.out.println("Quantidade do item " + itemParaAtualizar.getProduto().getName() + " atualizada para " + novaQuantidade + ".");
            } else {
                removerItem(codigoProduto);
            }
        } else {
            System.out.println("Item não encontrado no pedido.");
        }
    }
}