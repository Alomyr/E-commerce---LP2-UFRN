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


        Customer cliente = new Customer("User", 12345678910L);
        Stock estoque = new Stock();
        List<Customer> clientes = new ArrayList<>();
        
        Scanner scanner = new Scanner(System.in);
        

        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Gerenciar Estoque");
            System.out.println("2. Criar Novo Cliente");
            System.out.println("3. Fazer um Pedido");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcao) {
                case 1:
                    gerenciarEstoque(scanner, estoque);
                    break;
                case 2:
                    criarCliente(scanner, clientes);
                    break;
                case 3:
                    fazerPedido(scanner, estoque, clientes);
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
    
    public static void gerenciarEstoque(Scanner scanner, Stock estoque) {
        int opcaoEstoque = -1;
        while (opcaoEstoque != 0) {
            System.out.println("\n--- GERENCIAMENTO DE ESTOQUE ---");
            System.out.println("1. Adicionar Produto");
            System.out.println("2. Listar Produtos");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcaoEstoque = scanner.nextInt();
            scanner.nextLine();

            switch (opcaoEstoque) {
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
                    
                    new ProductPhysical(nomeProduto, preco, peso, quantidade, estoque);
                    System.out.println("Produto adicionado ao estoque.");
                    break;
                case 2:
                    System.out.println("\n--- PRODUTOS EM ESTOQUE ---");
                    estoque.ListToItems();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
    
    public static void criarCliente(Scanner scanner, List<Customer> clientes) {
        System.out.println("\n--- CRIAR NOVO CLIENTE ---");
        System.out.print("Nome do Cliente: ");
        String nomeCliente = scanner.nextLine();
        System.out.print("CPF do Cliente: ");
        long cpf = scanner.nextLong();
        scanner.nextLine();
        
        Customer novoCliente = new Customer(nomeCliente, cpf);
        clientes.add(novoCliente);
        System.out.println("Cliente " + novoCliente.getName() + " cadastrado com sucesso.");
    }
    
 public static void fazerPedido(Scanner scanner, Stock estoque, List<Customer> clientes) {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado. Por favor, crie um cliente primeiro.");
            return;
        }

        System.out.println("\n--- INICIANDO UM NOVO PEDIDO ---");
        System.out.print("Digite o CPF do cliente para o pedido: ");
        long cpfBusca = scanner.nextLong();
        scanner.nextLine();
        
        Customer clienteDoPedido = null;
        for (Customer c : clientes) {
            if (c.getCPF() == cpfBusca) {
                clienteDoPedido = c;
                break;
            }
        }
        
        if (clienteDoPedido == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        Order pedido = new Order(clienteDoPedido, estoque);
        
        int opcaoPedido = -1;
        while (opcaoPedido != 0) {
            System.out.println("\n--- GERENCIAR PEDIDO ---");
            System.out.println("Cliente: " + clienteDoPedido.getName());
            System.out.println("1. Adicionar Item");
            System.out.println("2. Remover Item");
            System.out.println("3. Atualizar Quantidade de um Item");
            System.out.println("4. Visualizar Resumo do Pedido");
            System.out.println("5. Finalizar Compra e Pagar");
            System.out.println("0. Cancelar Pedido e Voltar");
            System.out.print("Escolha uma opção: ");
            opcaoPedido = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcaoPedido) {
                case 1:
                    adicionarItemAoPedido(scanner, estoque, pedido);
                    break;
                case 2:
                    removerItemDoPedido(scanner, pedido);
                    break;
                case 3:
                    atualizarQuantidadeItem(scanner, pedido);
                    break;
                case 4:
                    visualizarResumo(pedido);
                    break;
                case 5:
                    finalizarCompra(scanner, pedido);
                    opcaoPedido = 0;
                    break;
                case 0:
                    System.out.println("Pedido cancelado. Os itens foram devolvidos ao estoque.");
                    return; 
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
    
    private static void adicionarItemAoPedido(Scanner scanner, Stock estoque, Order pedido) {
        System.out.println("\n--- ADICIONAR ITEM ---");
        System.out.print("Código do produto: ");
        String codigoProduto = scanner.nextLine();
        
        System.out.print("Quantidade desejada: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine();

        // Chamada simplificada para o novo método sobrecarregado
        pedido.addItem(codigoProduto, quantidade);
    }
    
    private static void removerItemDoPedido(Scanner scanner, Order pedido) {
        System.out.println("\n--- REMOVER ITEM ---");
        System.out.print("Código do produto para remover: ");
        String codigoRemover = scanner.nextLine();
        pedido.removeItem(codigoRemover);
    }
    
    private static void atualizarQuantidadeItem(Scanner scanner, Order pedido) {
        System.out.println("\n--- ATUALIZAR QUANTIDADE ---");
        System.out.print("Código do produto para atualizar: ");
        String codigoAtualizar = scanner.nextLine();
        System.out.print("Nova quantidade: ");
        int novaQtd = scanner.nextInt();
        scanner.nextLine();
        
        pedido.updateQuantity(codigoAtualizar, novaQtd);
    }
    
    private static void visualizarResumo(Order pedido) {
        System.out.println("\n--- RESUMO DO PEDIDO ---");
        System.out.println("Total dos itens: R$ " + pedido.calculateTotal());
        System.out.println("Frete total: R$ " + pedido.calculateTotalShipping());
        System.out.println("Valor final a pagar: R$ " + (pedido.calculateTotal() + pedido.calculateTotalShipping()));
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