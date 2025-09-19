package Ecommerce;

public class PagamentoCartao extends Pagamento {
 //sobrescrevendo processar
    public PagamentoCartao(double valor) {
        super(valor);
    }

    @Override
    public void processar() {
        System.out.println("Processando pagamento via Cartão de Crédito no valor de R$ " + this.val);
        System.out.println("Transação aprovada.");

    }


}
