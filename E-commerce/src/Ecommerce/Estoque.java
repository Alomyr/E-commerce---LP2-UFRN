package Ecommerce;

import java.util.ArrayList;
import java.util.List;

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
}
