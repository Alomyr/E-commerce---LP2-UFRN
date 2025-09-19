package Ecommerce.model;

import Ecommerce.service.Estoque;

public class ProductF extends product{

    private double peso;
    private double taxa = 0.5;

    public ProductF(String name, double price, double peso,int qtd, Estoque estoque) {
        super(name, price, qtd);
        this.peso = peso;
        estoque.addItens(this);
    } 
    public double getPeso() {
        return peso;
    }

    @Override
    public double frete() {
        return this.peso * taxa;
    }
}
