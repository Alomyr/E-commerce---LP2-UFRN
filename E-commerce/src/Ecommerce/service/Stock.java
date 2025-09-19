package Ecommerce.service;

import Ecommerce.model.product;
import java.util.ArrayList;
import java.util.List;

public class Stock {

    private List<product> itens = new ArrayList<>();
    public void addItems(product product){
        this.itens.add(product);
    }
    public void remuveItens(product product){
        this.itens.remove(product);
    }
        public void removeItemsToAmount(String codigo, int quantidadeParaRemover) {
        for (product item : itens) {
            if (item.getCode().equals(codigo)) {
                int newAmount = item.getqtd() - quantidadeParaRemover;
                if (newAmount >= 0) {
                    item.setAmount(newAmount);
                    System.out.println("Estoque atualizado para o produto " + item.getName() + ". Nova quantidade: " + newAmount);
                    // if (novaQuantidade == 0) {
                    //     itens.remove(item);
                    // }
                } else {
                    System.out.println("Erro: Quantidade insuficiente em estoque para o produto " + item.getName());
                }
                return;
            }
        }
        System.out.println("Produto com código " + codigo + " não encontrado no estoque.");
    }
    public product searchProductByCode(String codigo) {
        for (product item : this.itens) {
            if (item.getCode().equals(codigo)) {
                return item;
            }
        }
        return null;
    }
    public void ListToItems() {
        if (this.itens.isEmpty()) {
            System.out.println("O estoque está vazio.");
        } else {
            System.out.println("--- Produtos em Estoque ---");
            for (product item : this.itens) {
                System.out.println("Nome: " + item.getName());
                System.out.println("Código: " + item.getCode());
                System.out.println("Preço: R$ " + item.getPrice());
                System.out.println("Quantidade: " + item.getqtd());
                System.out.println("--------------------------");
            }
        }
    }
    public void increaseQuantity(String codigo, int quantidadeParaAdicionar) {
        for (product item : itens) {
            if (item.getCode().equals(codigo)) {
                int newAmount = item.getqtd() + quantidadeParaAdicionar;
                item.setAmount(newAmount);
                System.out.println("Estoque atualizado para o produto " + item.getName() + ". Nova quantidade: " + newAmount);
                return;
            }
        }
        System.out.println("Erro: Produto com código " + codigo + " não encontrado para adicionar ao estoque.");
    }
}
