package Ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import Ecommerce.model.product;

public class Estoque {

    private List<product> itens = new ArrayList<>();
    public void addItens(product product){
        this.itens.add(product);
    }
    public void remuveItens(product product){
        this.itens.remove(product);
    }
        public void removerItensPorQuantidade(String codigo, int quantidadeParaRemover) {
        for (product item : itens) {
            if (item.getCode().equals(codigo)) {
                int novaQuantidade = item.getqtd() - quantidadeParaRemover;
                if (novaQuantidade >= 0) {
                    item.setQuantidade(novaQuantidade);
                    System.out.println("Estoque atualizado para o produto " + item.getName() + ". Nova quantidade: " + novaQuantidade);
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
    public product buscarProdutoPorCodigo(String codigo) {
        for (product item : this.itens) {
            if (item.getCode().equals(codigo)) {
                return item;
            }
        }
        return null;
    }
    public void listarItens() {
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
    public void aumentarQuantidade(String codigo, int quantidadeParaAdicionar) {
        for (product item : itens) {
            if (item.getCode().equals(codigo)) {
                int novaQuantidade = item.getqtd() + quantidadeParaAdicionar;
                item.setQuantidade(novaQuantidade);
                System.out.println("Estoque atualizado para o produto " + item.getName() + ". Nova quantidade: " + novaQuantidade);
                return;
            }
        }
        System.out.println("Erro: Produto com código " + codigo + " não encontrado para adicionar ao estoque.");
    }
}
