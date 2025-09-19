package Ecommerce;

import Ecommerce.model.Customer;
import Ecommerce.model.Order;
import Ecommerce.model.Payment;
import Ecommerce.model.PaymentCard;
import Ecommerce.model.PaymentPix;
import Ecommerce.model.ProductPhysical;
import Ecommerce.service.Stock;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
 
public class Main {
    public static void main(String[] args) throws Exception {


        Customer customer = new Customer("User", 12345678910L);
        Stock stock = new Stock();
        List<Customer> customers = new ArrayList<>();
        
        Scanner scanner = new Scanner(System.in);
        

        int option = -1;
        while (option != 0) {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Gerenciar Estoque");
            System.out.println("2. Criar Novo Cliente");
            System.out.println("3. Fazer um Pedido");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            option = scanner.nextInt();
            scanner.nextLine();
            
            switch (option) {
                case 1:
                    manageStock(scanner, stock);
                    break;
                case 2:
                    creatCustomer(scanner, customers);
                    break;
                case 3:
                    placeOrder(scanner, stock, customers);
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
        
        scanner.close();
    }
    
    public static void manageStock(Scanner scanner, Stock stock) {
        int optionInStock = -1;
        while (optionInStock != 0) {
            System.out.println("\n--- GERENCIAMENTO DE ESTOQUE ---");
            System.out.println("1. Adicionar Produto");
            System.out.println("2. Listar Produtos");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            optionInStock = scanner.nextInt();
            scanner.nextLine();

            switch (optionInStock) {
                case 1:
                    System.out.print("Nome do Produto: ");
                    String nomeProduto = scanner.nextLine();
                    System.out.print("Preço: ");
                    double preco = scanner.nextDouble();
                    System.out.print("Peso (kg): ");
                    double peso = scanner.nextDouble();
                    System.out.print("Quantidade: ");
                    int quantidade = scanner.nextInt();
                    scanner.nextLine();
                    
                    new ProductPhysical(nomeProduto, preco, peso, quantidade, stock);
                    System.out.println("Produto adicionado ao estoque.");
                    break;
                case 2:
                    System.out.println("\n--- PRODUTOS EM ESTOQUE ---");
                    stock.ListToItems();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
    
    public static void creatCustomer(Scanner scanner, List<Customer> clientes) {
        System.out.println("\n--- CRIAR NOVO CLIENTE ---");
        System.out.print("Nome do Cliente: ");
        String nameCustomer = scanner.nextLine();
        System.out.print("CPF do Cliente: ");
        long cpf = scanner.nextLong();
        scanner.nextLine();
        
        Customer newCostumer = new Customer(nameCustomer, cpf);
        clientes.add(newCostumer);
        System.out.println("Cliente " + newCostumer.getName() + " cadastrado com sucesso.");
    }
    
 public static void placeOrder(Scanner scanner, Stock stock, List<Customer> customer) {
        if (customer.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado. Por favor, crie um cliente primeiro.");
            return;
        }

        System.out.println("\n--- INICIANDO UM NOVO PEDIDO ---");
        System.out.print("Digite o CPF do cliente para o pedido: ");
        long cpfSearch = scanner.nextLong();
        scanner.nextLine();
        
        Customer placeToCustomer = null;
        for (Customer c : customer) {
            if (c.getCPF() == cpfSearch) {
                placeToCustomer = c;
                break;
            }
        }
        
        if (placeToCustomer == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        Order order = new Order(placeToCustomer, stock);
        
        int RequestOption = -1;
        while (RequestOption != 0) {
            System.out.println("\n--- GERENCIAR PEDIDO ---");
            System.out.println("Cliente: " + placeToCustomer.getName());
            System.out.println("1. Adicionar Item");
            System.out.println("2. Remover Item");
            System.out.println("3. Atualizar Quantidade de um Item");
            System.out.println("4. Visualizar Resumo do Pedido");
            System.out.println("5. Finalizar Compra e Pagar");
            System.out.println("0. Cancelar Pedido e Voltar");
            System.out.print("Escolha uma opção: ");
            RequestOption = scanner.nextInt();
            scanner.nextLine();
            
            switch (RequestOption) {
                case 1:
                    adicionarItemAoPedido(scanner, stock, order);
                    break;
                case 2:
                    removeItemOfOrder(scanner, order);
                    break;
                case 3:
                    upateAmountItem(scanner, order);
                    break;
                case 4:
                    viewSummary(order);
                    break;
                case 5:
                    finalizarCompra(scanner, order);
                    RequestOption = 0;
                    break;
                case 0:
                    System.out.println("Pedido cancelado. Os itens foram devolvidos ao estoque.");
                    return; 
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
    
    private static void adicionarItemAoPedido(Scanner scanner, Stock stock, Order order) {
        System.out.println("\n--- ADICIONAR ITEM ---");
        System.out.print("Código do produto: ");
        String productCode = scanner.nextLine();
        
        System.out.print("Quantidade desejada: ");
        int amount = scanner.nextInt();
        scanner.nextLine();

        // Chamada simplificada para o novo método sobrecarregado
        order.addItem(productCode, amount);
    }
    
    private static void removeItemOfOrder(Scanner scanner, Order order) {
        System.out.println("\n--- REMOVER ITEM ---");
        System.out.print("Código do produto para remover: ");
        String removeCode = scanner.nextLine();
        order.removeItem(removeCode);
    }
    
    private static void upateAmountItem(Scanner scanner, Order pedido) {
        System.out.println("\n--- ATUALIZAR QUANTIDADE ---");
        System.out.print("Código do produto para atualizar: ");
        String upadateCode = scanner.nextLine();
        System.out.print("Nova quantidade: ");
        int newAmount = scanner.nextInt();
        scanner.nextLine();
        
        pedido.updateQuantity(upadateCode, newAmount);
    }
    
    private static void viewSummary(Order order) {
        System.out.println("\n--- RESUMO DO PEDIDO ---");
        System.out.println("Total dos itens: R$ " + order.calculateTotal());
        System.out.println("Frete total: R$ " + order.calculateTotalShipping());
        System.out.println("Valor final a pagar: R$ " + (order.calculateTotal() + order.calculateTotalShipping()));
    }
    
    private static void finalizarCompra(Scanner scanner, Order pedido) {
        System.out.println("\n--- FINALIZAR COMPRA ---");
        System.out.print("Escolha a forma de pagamento (Cartao/Pix): ");
        String formaPagamento = scanner.nextLine();
        
        if (formaPagamento.equalsIgnoreCase("Cartao") || formaPagamento.equalsIgnoreCase("C")) {
            Payment pagto = new PaymentCard(pedido.calculateTotal() + pedido.calculateTotalShipping());
            pedido.makePayment(pagto);
        } else if (formaPagamento.equalsIgnoreCase("Pix") || formaPagamento.equalsIgnoreCase("P")) {
            Payment pagto = new PaymentPix(pedido.calculateTotal() + pedido.calculateTotalShipping());
            pedido.makePayment(pagto);
        } else {
            System.out.println("Forma de pagamento inválida. Pedido não finalizado.");
        }
    }
}