
import Ecommerce.Customer;
import Ecommerce.Estoque;
import Ecommerce.Pagamento;
import Ecommerce.PagamentoCartao;
import Ecommerce.PagamentoPix;
import Ecommerce.Pedido;
import Ecommerce.ProductF;
import Ecommerce.product;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
 
public class Main {//arrumar clientes, arrumar estoque e fazer o menu interativo na main posso fazer por maquina de estados
    public static void main(String[] args) throws Exception {


        Customer cliente = new Customer("User", 12345678910L);
        Estoque estoque = new Estoque();
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
    
    public static void gerenciarEstoque(Scanner scanner, Estoque estoque) {
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
                    
                    new ProductF(nomeProduto, preco, peso, quantidade, estoque);
                    System.out.println("Produto adicionado ao estoque.");
                    break;
                case 2:
                    System.out.println("\n--- PRODUTOS EM ESTOQUE ---");
                    estoque.listarItens(); // Você precisaria adicionar este método na sua classe Estoque
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
    
    public static void fazerPedido(Scanner scanner, Estoque estoque, List<Customer> clientes) {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado. Por favor, crie um cliente primeiro.");
            return;
        }

        System.out.println("\n--- FAZER UM PEDIDO ---");
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

        Pedido pedido = new Pedido(clienteDoPedido, estoque);
        
        String adicionarMais = "sim";
        while (adicionarMais.equalsIgnoreCase("sim")) {
            System.out.print("Digite o código do produto para adicionar ao pedido: ");
            String codigoProduto = scanner.nextLine();
            
            product produtoEncontrado = estoque.buscarProdutoPorCodigo(codigoProduto);

            if (produtoEncontrado != null) {
                System.out.print("Digite a quantidade: ");
                int quantidade = scanner.nextInt();
                scanner.nextLine();

                if (quantidade <= produtoEncontrado.getqtd()) {
                    pedido.adicionarItem(produtoEncontrado, quantidade);
                    System.out.println(quantidade + "x " + produtoEncontrado.getName() + " adicionado ao pedido.");
                } else {
                    System.out.println("Quantidade insuficiente em estoque.");
                }
            } else {
                System.out.println("Produto não encontrado.");
            }
            
            System.out.print("Adicionar mais itens? (sim/nao): ");
            adicionarMais = scanner.nextLine();
        }
        
        System.out.println("\n--- RESUMO DO PEDIDO ---");
        System.out.println("Cliente: " + clienteDoPedido.getName() + " CPF: " + clienteDoPedido.getCPF());
        System.out.println("Total do pedido: R$ " + pedido.calcularTotal());
        System.out.println("Frete total: R$ " + pedido.calcularFreteTotal());
        double valorFinal = pedido.calcularTotal() + pedido.calcularFreteTotal();
        System.out.println("Valor final a pagar: R$ " + valorFinal);
        
        System.out.print("\nEscolha a forma de pagamento (Cartao/Pix): ");
        String formaPagamento = scanner.nextLine();
        
        if (formaPagamento.equalsIgnoreCase("Cartao")||formaPagamento.equalsIgnoreCase("C")||formaPagamento.equalsIgnoreCase("c")) {
            Pagamento pagto = new PagamentoCartao(valorFinal);
            pedido.realizarPagamento(pagto);
        } else if (formaPagamento.equalsIgnoreCase("Pix")||formaPagamento.equalsIgnoreCase("p")||formaPagamento.equalsIgnoreCase("P")) {
            Pagamento pagto = new PagamentoPix(valorFinal);
            pedido.realizarPagamento(pagto);
        } else {
            System.out.println("Forma de pagamento inválida. Pedido cancelado.");
        }
    }
}