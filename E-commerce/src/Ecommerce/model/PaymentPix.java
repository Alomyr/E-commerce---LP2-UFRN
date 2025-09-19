package Ecommerce.model;

public class PaymentPix extends Payment{
    public PaymentPix(double value) {
        super(value);
    }

    @Override
    public void process() {
        System.out.println("Processando pagamento via PIX no valor de R$ " + this.value);
        System.out.println("Gerando QR Code... Pagamento pendente.");
    }

}
