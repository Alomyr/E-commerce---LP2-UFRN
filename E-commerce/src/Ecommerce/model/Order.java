package Ecommerce.model;

import Ecommerce.service.Stock;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private Customer customer;
    private List<OrderedItems> items = new ArrayList<>();
    private Stock stock;

    public Order(Customer customer, Stock estoque){
        this.customer = customer;
        this.stock = estoque;
    }
    public void addItem(product produto, int amount) {
        OrderedItems novoItem = new OrderedItems(produto, amount);
        this.items.add(novoItem);
    }

    public void addItem(String codeProduct, int amount) {
    // Busca o produto no estoque usando o código
        product productFound = this.stock.searchProductByCode(codeProduct);

        if (productFound != null) {
            // Verifica se a quantidade está disponível antes de adicionar
            if (amount <= productFound.getqtd()) {
                this.addItem(productFound, amount); // Chama o método original
                this.stock.removeItemsToAmount(codeProduct, amount); // Remove do estoque
                System.out.println(amount + "x " + productFound.getName() + " adicionado ao pedido.");
            } else {
                System.out.println("Quantidade insuficiente em estoque para " + productFound.getName());
            }
        } else {
            System.out.println("Produto com código " + codeProduct + " não encontrado no estoque.");
        }
    }

    public double calculateTotal(){
        double total=0.0;
         for (OrderedItems item : items) {
            total += item.getSubtotal();
        }
        return total;
    }
      public double calculateTotalShipping() {
        double freteTotal = 0.0;
        for (OrderedItems item : items) {
            freteTotal += item.getProduct().freight();
        }
        return freteTotal;
    }
    private void DropInStock() {
        System.out.println("Dando baixa nos itens do estoque...");
        for (OrderedItems item : items) {
            this.stock.removeItemsToAmount(item.getProduct().getCode(), item.getAmount());
        }
    }
    public void makePayment(Payment pagto) {
        pagto.process();
        DropInStock(); //esta removendo de forma duplicada
    }
public void removeItem(String codeProduct) {
    OrderedItems itemToRemove = null;
    for (OrderedItems item : items) {
        if (item.getProduct().getCode().equals(codeProduct)) {
            itemToRemove = item;
            break;
        }
    }

    if (itemToRemove != null) {
        this.stock.increaseQuantity(itemToRemove.getProduct().getCode(), itemToRemove.getAmount());
        this.items.remove(itemToRemove);
        System.out.println("Item com código " + codeProduct + " removido do pedido.");
    } else {
        System.out.println("Item não encontrado no pedido.");
    }
}

    public void updateQuantity(String codeProduct, int newQuantity) {
        OrderedItems itemToUpadate = null;
        for (OrderedItems item : items) {
            if (item.getProduct().getCode().equals(codeProduct)) {
                itemToUpadate = item;
                break;
            }
        }

        if (itemToUpadate != null) {
            int quantidadeAnterior = itemToUpadate.getAmount();
            int difference = newQuantity - quantidadeAnterior;

            if (difference > 0) {
                this.stock.removeItemsToAmount(codeProduct, difference);
            } else if (difference < 0) {
                this.stock.increaseQuantity(codeProduct, -difference);
            }

            if (newQuantity > 0) {
                itemToUpadate.setAmount(newQuantity);
                System.out.println("Quantidade do item " + itemToUpadate.getProduct().getName() + " atualizada para " + newQuantity + ".");
            } else {
                removeItem(codeProduct);
            }
        } else {
            System.out.println("Item não encontrado no pedido.");
        }
    }
}