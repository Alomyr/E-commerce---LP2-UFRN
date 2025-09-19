package Ecommerce.model;

public class PaymentCard extends Payment {
 //sobrescrevendo processar
    public PaymentCard(double value) {
        super(value);
    }

    @Override
    public void process() {
        System.out.println("Processando pagamento via Cartão de Crédito no valor de R$ " + this.value);
        System.out.println("Transação aprovada.");

    }


}
