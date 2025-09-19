package Ecommerce;

public class ItensPedidos{

    private product product;
    private int quantidade;

    public ItensPedidos(product product, int quantidade){
        this.product = product;
        this.quantidade = quantidade;
    }
    public double getSubtotal(){
        return this.product.getPrice() * this.quantidade;
    }
    public product getProduto() {
        return this.product;
    }
    
}

