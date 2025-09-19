
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

        Pedido pedido = new Pedido(clienteDoPedido, estoque);
        
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
                    opcaoPedido = 0; // Sai do loop de gerenciamento
                    break;
                case 0:
                    System.out.println("Pedido cancelado. Os itens foram devolvidos ao estoque.");
                    // Lógica para devolver todos os itens do pedido ao estoque, se necessário
                    return; // Retorna para o menu principal
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
    
    private static void adicionarItemAoPedido(Scanner scanner, Estoque estoque, Pedido pedido) {
        System.out.println("\n--- ADICIONAR ITEM ---");
        System.out.print("Código do produto: ");
        String codigoProduto = scanner.nextLine();
        
        product produtoEncontrado = estoque.buscarProdutoPorCodigo(codigoProduto);
        
        if (produtoEncontrado != null) {
            System.out.print("Quantidade desejada: ");
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
    }
    
    private static void removerItemDoPedido(Scanner scanner, Pedido pedido) {
        System.out.println("\n--- REMOVER ITEM ---");
        System.out.print("Código do produto para remover: ");
        String codigoRemover = scanner.nextLine();
        pedido.removerItem(codigoRemover);
    }
    
    private static void atualizarQuantidadeItem(Scanner scanner, Pedido pedido) {
        System.out.println("\n--- ATUALIZAR QUANTIDADE ---");
        System.out.print("Código do produto para atualizar: ");
        String codigoAtualizar = scanner.nextLine();
        System.out.print("Nova quantidade: ");
        int novaQtd = scanner.nextInt();
        scanner.nextLine();
        
        pedido.atualizarQuantidade(codigoAtualizar, novaQtd);
    }
    
    private static void visualizarResumo(Pedido pedido) {
        System.out.println("\n--- RESUMO DO PEDIDO ---");
        System.out.println("Total dos itens: R$ " + pedido.calcularTotal());
        System.out.println("Frete total: R$ " + pedido.calcularFreteTotal());
        System.out.println("Valor final a pagar: R$ " + (pedido.calcularTotal() + pedido.calcularFreteTotal()));
    }
    
    private static void finalizarCompra(Scanner scanner, Pedido pedido) {
        System.out.println("\n--- FINALIZAR COMPRA ---");
        System.out.print("Escolha a forma de pagamento (Cartao/Pix): ");
        String formaPagamento = scanner.nextLine();
        
        if (formaPagamento.equalsIgnoreCase("Cartao") || formaPagamento.equalsIgnoreCase("C")) {
            Pagamento pagto = new PagamentoCartao(pedido.calcularTotal() + pedido.calcularFreteTotal());
            pedido.realizarPagamento(pagto);
        } else if (formaPagamento.equalsIgnoreCase("Pix") || formaPagamento.equalsIgnoreCase("P")) {
            Pagamento pagto = new PagamentoPix(pedido.calcularTotal() + pedido.calcularFreteTotal());
            pedido.realizarPagamento(pagto);
        } else {
            System.out.println("Forma de pagamento inválida. Pedido não finalizado.");
        }
    }
}