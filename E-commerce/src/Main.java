
import Ecommerce.Customer;
import Ecommerce.Estoque;
import Ecommerce.Pedido;
import Ecommerce.ProductF;
 
public class Main {//arrumar clientes, arrumar estoque e fazer o menu interativo na main posso fazer por maquina de estados
    public static void main(String[] args) throws Exception {


        Customer cliente = new Customer("c1", 12345678910L);
        System.out.println("Cliente criado: " + cliente.getName());
        System.out.println("----------------------------\n");
        
        Estoque estoque1 = new Estoque();
        
        ProductF pc = new ProductF("pc", 100, 100, 1, estoque1);
        System.out.println("Produto adicionado ao estoque: " + pc.getName());

        ProductF fone = new ProductF("fone", 15, 8, 2, estoque1);
        System.out.println("Produto adicionado ao estoque: " + fone.getName());
        
        System.out.println("--- 3. Criando um Pedido ---");
        Pedido pedido1 = new Pedido(cliente);

        pedido1.adicionarItem(fone, 1);
        pedido1.adicionarItem(pc, 1);

        

    }
}