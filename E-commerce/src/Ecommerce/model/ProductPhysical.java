package Ecommerce.model;

import Ecommerce.service.Stock;

public class ProductPhysical extends product{

    private double weight;
    private double taxa = 0.5;

    public ProductPhysical(String name, double price, double peso,int qtd, Stock stock) {
        super(name, price, qtd);
        this.weight = peso;
        stock.addItems(this);
    } 
    public double getWeight() {
        return weight;
    }

    @Override
    public double freight() {
        return this.weight * taxa;
    }
}
