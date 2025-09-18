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

}
