# Mini Sistema de E-commerce em Java

## 📜 Visão Geral

Este é um projeto de estudo em Java que simula um sistema de e-commerce interativo via terminal. O objetivo é aplicar e demonstrar conceitos essenciais de Programação Orientada a Objetos (POO), como Herança, Polimorfismo, Agregação e Composição, em um ambiente prático. O sistema permite o gerenciamento de estoque, cadastro de clientes e a realização de pedidos com múltiplas funcionalidades.

## ✨ Funcionalidades

O sistema oferece as seguintes funcionalidades interativas:

    Gerenciamento de Estoque: Permite adicionar novos produtos físicos ao estoque e visualizar os itens e suas respectivas quantidades disponíveis.

    Cadastro de Clientes: Possibilita o cadastro de múltiplos clientes com nome e CPF.

    Criação e Gestão de Pedidos: O usuário pode iniciar um pedido para um cliente específico e gerenciá-lo em tempo real, adicionando, removendo ou atualizando a quantidade de itens no carrinho antes de finalizar a compra.

    Cálculo Automático: O sistema calcula o subtotal de cada item, o valor total do pedido e o frete com base no peso dos produtos.

    Processamento de Pagamento: O pedido é finalizado com o pagamento, que pode ser feito por Cartão ou PIX, utilizando o conceito de polimorfismo.

## 🛠️ Requisitos de POO Demonstrados

O projeto foi estruturado para ilustrar os principais conceitos de POO:

    Herança e Polimorfismo: As classes PagamentoCartao e PagamentoPix herdam da classe abstrata Pagamento. O método processar() é sobrescrito em cada subclasse para implementar uma lógica de pagamento específica para cada forma, demonstrando o polimorfismo em ação. A classe ProductF herda de product e implementa seu próprio cálculo de frete.

    Agregação: A classe Pedido possui uma referência a um objeto Customer. O Customer existe independentemente do Pedido, ou seja, o cliente pode existir mesmo que não tenha feito um pedido.

    Composição: A classe Pedido contém uma lista de objetos ItensPedidos. A vida desses itens está diretamente ligada ao pedido; se o pedido for cancelado ou finalizado, os itens do pedido perdem seu contexto e são manipulados pelo sistema.

    Encapsulamento: Atributos importantes como name, price, peso, quantidade e CPF são privados em suas respectivas classes, sendo acessados ou modificados apenas através de métodos públicos (getters e setters), garantindo a integridade dos dados.

## 🚀 Como Executar o Projeto

    Compile as classes: Navegue até o diretório src/Ecommerce e use o compilador Java.
    Bash

javac *.java

Execute o programa: A partir do mesmo diretório, execute a classe principal.
Bash

java Main

Interaja com o Menu: Siga as instruções no terminal para navegar entre as opções de gerenciamento de estoque, cadastro de clientes e realização de pedidos.


    src/
    └── Ecommerce/
        ├── Main.java
        ├── model/
        │   ├── Customer.java
        │   ├── ItensPedidos.java
        │   ├── Pagamento.java
        │   ├── Pedido.java
        │   │── product.java
        │   │── ProductF.java
        │   │── PagamentoCartao.java
        │   │── PagamentoPix.java
        └── services/
            └── Estoque.java