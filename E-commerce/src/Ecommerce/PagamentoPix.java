package Ecommerce;

public class PagamentoPix extends Pagamento{
 //sobrescrevendo processar

    public PagamentoPix(double valor) {
        super(valor);
    }

    @Override
    public void processar() {
        System.out.println("Processando pagamento via PIX no valor de R$ " + this.val);
        System.out.println("Gerando QR Code... Pagamento pendente.");
    }

}
