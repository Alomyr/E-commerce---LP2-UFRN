/*
Implementar um mini sistema de e-commerce que permita cadastrar
produtos e clientes, criar pedidos com itens, calcular total, aplicar
frete/pagamento usando polimorfismo, e demonstrar composição e
agregação de forma explícita.

    REQUISITO 1 - 0,4PT
    ▪ Herança + Override (polimorfismo dinâmico)
    ▪ Uma classe abstrata Pagamento com método abstrato processar(double valor)
    ▪ Duas subclasses, por exemplo: PagamentoCartao e PagamentoPix, sobrescrevendo
    processar(...).

    REQUISITO 2 - 0,4PT
    ▪ Overload (polimorfismo estático)
    ▪ Pelo menos dois métodos sobrecarregados (mesmo nome, assinaturas distintas).
    ▪ Ex.:
    ▪ calcularFrete(double peso) e calcularFrete(double peso, String cep)
    ▪ adicionarItem(Produto p, int qtd) e adicionarItem(String codP, int qtd)

    REQUISITO 3 - 0,4PT
    ▪ Composição (relação “parte-todo” forte)
    ▪ Pedido tem ItemPedido (lista interna).
    ▪ Se Pedido for removido, seus ItemPedido também são — vida atrelada ao todo.

    REQUISITO 4 - 0,4PT
    ▪ Agregação (relação fraca)
    ▪ Pedido agrega um Cliente.
    ▪ Cliente existe independente do Pedido.
    
    REQUISITO 5 - 0,4PT
    ▪ Encapsulamento
    ▪ Atributos privados, acesso por getters/setters com validação mínima.


classDiagram
    direction LR
    class estoque

    class Cliente {
        -String nome
        -String cpf
        +getNome()
    }

    class Produto {
        -String nome
        -double preco
        -String codigo
        +getNome()
        +getPreco()
        +setPreco(double)
    }

    class Pagamento {
        <<abstract>>
        #double valor
        +Pagamento(valor)
        +processar()
    }
    
    class PagamentoCartao {
        +PagamentoCartao(valor)
        +processar()
    }

    class PagamentoPix {
        +PagamentoPix(valor)
        +processar()
    }

    class ItemPedido {
        -Produto produto
        -int quantidade
        +getSubtotal()
    }
    
    class Pedido {
        -Cliente cliente
        -List~ItemPedido~ itens
        -double total
        +adicionarItem(Produto, int)
        +adicionarItem(String, int)
        +calcularTotal()
        +exibirDetalhes()
    }

    Pagamento <|-- PagamentoCartao : Herança
    Pagamento <|-- PagamentoPix : Herança
    
    Pedido "1" *-- "0..*" ItemPedido : Composição
    Pedido "1" -- "1" Cliente : Agregação
    ItemPedido "1" -- "1" Produto : Associação

 */

 
public class Main {
        public static void main(String[] args) throws Exception {
        System.out.println("Hello world");
    }
}