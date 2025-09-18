package Ecommerce;

import java.util.Random;

public abstract class product {

   private  String name;
   private  String code;
   private int quantidade;
   private  double price;
   private static final String CARACTERES_ALFABETICOS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
   private static final Random RANDOM = new Random();

   
   // standard builder
   public product(){
      this.name="Product";
      this.code="code123";
      this.quantidade=1;
      this.price=0.0;
   }
   // parameterized builder
   public product(String name, double price, int qtd){
      this.name=name;
      this.price=price;
      this.quantidade=qtd;
      this.code = gerarCodigo();

   }

   private static String gerarCodigo() {
        // Parte fixa
        String parteFixa = "2025";

        // Gerar a primeira letra aleatória
        char letra1 = CARACTERES_ALFABETICOS.charAt(RANDOM.nextInt(CARACTERES_ALFABETICOS.length()));

        // Gerar o primeiro número aleatório (entre 0 e 99)
        int numero1 = RANDOM.nextInt(100);

        // Gerar a segunda letra aleatória
        char letra2 = CARACTERES_ALFABETICOS.charAt(RANDOM.nextInt(CARACTERES_ALFABETICOS.length()));

        // Gerar o segundo número aleatório (entre 0 e 9)
        int numero2 = RANDOM.nextInt(10);
        
        // Formatar o número para ter pelo menos 2 dígitos, se necessário (ex: 7 -> 07)
        String numero1Formatado = String.format("%02d", numero1);
        
        // Combinar todas as partes
        return parteFixa + letra1 + numero1Formatado + letra2 + numero2;
    }


   void SetName(String name){
      this.name = name;
   }
   void SetCode(String code){
      this.code=code;
   }
   
   public double getPrice(){
      return this.price;
   } 
   public String getName(){
   return this.name;
}
   public abstract double frete();
}
/*
 * 
 *     ▪ calcularFrete(double peso) e calcularFrete(double peso, String cep)
    ▪ adicionarItem(Produto p, int qtd) e adicionarItem(String codP, int qtd)
 */