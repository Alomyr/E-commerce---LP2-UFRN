package Ecommerce.model;

public class OrderedItems{

    private product product;
    private int amount;

    public OrderedItems(product product, int amount){
        this.product = product;
        this.amount = amount;
    }
    public double getSubtotal(){
        return this.product.getPrice() * this.amount;
    }
    public product getProduct() {
        return this.product;
    }
    public int getAmount(){
      return this.amount;
   }
    void setAmount(int amount){
        this.amount = amount;
    }
    
}

